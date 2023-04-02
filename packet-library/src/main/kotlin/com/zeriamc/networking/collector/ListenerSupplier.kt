package com.zeriamc.networking.collector

import com.esotericsoftware.kryonet.Listener
import com.zeriamc.networking.packets.Packet
import kotlin.reflect.KClass

interface ListenerSupplier {
    fun getServerListeners(): Collection<KClass<out Listener>>
    fun getPacketListeners(): Collection<KClass<*>>
}