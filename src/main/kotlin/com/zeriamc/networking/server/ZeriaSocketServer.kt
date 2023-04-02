package com.zeriamc.networking.server

import com.esotericsoftware.kryonet.Server
import com.zeriamc.logging.Logger
import com.zeriamc.networking.collector.ListenerCollector.registerListeners
import com.zeriamc.networking.collector.ListenerSupplier
import com.zeriamc.networking.collector.PacketCollector.registerPackets
import com.zeriamc.networking.packets.client.proxy.StopProxyPacket
import com.zeriamc.networking.server.session.SessionManager.sessionOrNull
import kotlin.concurrent.fixedRateTimer

object ZeriaSocketServer {
    private lateinit var server: Server

    fun initialize(listener: ListenerSupplier, port: Int, logger: Logger) {
        logger.info("Starting ZeriaMC Network...")

        if (listener.getServerListeners().isEmpty())
            logger.warning("The Server listener is empty.")
        if (listener.getPacketListeners().isEmpty())
            logger.warning("The Packet listener is empty.")
        if (port >= 65535)
            logger.severe("The port can't exceed 65535.")

        this.server = Server().apply {
            start()
            bind(port)
            registerPackets()
            registerListeners(listener)
        }

        fixedRateTimer("Keep Alive Inspector", true, 0, 1000 * 30) {
            server.connections.mapNotNull { it.sessionOrNull }
                .filter {
                    val lastKeepAlive = it.metadata["last_keep_alive"] as? Long ?: it.createdAt
                    System.currentTimeMillis() - lastKeepAlive > 1000 * 60 * 5
                }.forEach {
                    this@ZeriaSocketServer.server.sendToAllTCP(StopProxyPacket(it.uuid))
                    it.connection.close()
                }
        }
    }

    fun getServer(): Server {
        return this.server
    }
}