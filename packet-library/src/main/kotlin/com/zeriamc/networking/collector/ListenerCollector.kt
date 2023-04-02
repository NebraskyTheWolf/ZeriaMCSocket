package com.zeriamc.networking.collector

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.EndPoint
import com.esotericsoftware.kryonet.Listener
import com.google.common.util.concurrent.ThreadFactoryBuilder
import com.zeriamc.networking.logger.SocketLogger
import java.util.concurrent.Executors
import kotlin.properties.Delegates
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.jvmErasure

object ListenerCollector {
    var packetListeners by Delegates.notNull<Collection<KClass<*>>>()
        private set

    var serverListeners by Delegates.notNull<Collection<KClass<out Listener>>>()
        private set

    private val threadPool = Executors.newCachedThreadPool(ThreadFactoryBuilder().setNameFormat("Listener-Executor-%d").build())

    private fun collectListeners(supplier: ListenerSupplier) {
        packetListeners = supplier.getPacketListeners()
        serverListeners = supplier.getServerListeners()

        SocketLogger.logger.info("Collected ${packetListeners.size} packet listeners: " + packetListeners.joinToString { it.simpleName!! })
        SocketLogger.logger.info("Collected ${serverListeners.size} server listeners: " + serverListeners.joinToString { it.simpleName!! })
    }

    fun EndPoint.registerListeners(supplier: ListenerSupplier) {
        collectListeners(supplier)
        packetListeners.forEach { addListener(it.createListener()) }
        serverListeners.forEach { addListener(Listener.ThreadedListener(it.createInstance(), threadPool)) }
    }

    private fun KClass<*>.createListener(): Listener = Listener.ThreadedListener(
        object : Listener() {
            val instance = createInstance()
            val functions = declaredFunctions.filter {
                it.functionParameters.size == 2 && it.functionParameters[0].type.jvmErasure == Connection::class
            }.groupBy {
                it.functionParameters[1].type.jvmErasure
            }

            override fun received(connection: Connection, incoming: Any) {
                try {
                    functions[incoming::class]?.forEach { it.call(instance, connection, incoming) }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    SocketLogger.logger.severe("Could not dispatch packet-receive to $simpleName for incoming packet $incoming on connection $connection: ${e.localizedMessage}")
                }
            }
        }, threadPool
    )

    private val KFunction<*>.functionParameters
        get() = parameters.filter { param -> param.kind == KParameter.Kind.VALUE }
}