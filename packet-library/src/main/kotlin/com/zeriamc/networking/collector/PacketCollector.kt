package com.zeriamc.networking.collector

import com.esotericsoftware.kryonet.EndPoint
import com.zeriamc.networking.logger.SocketLogger
import com.zeriamc.networking.packets.Packet
import com.zeriamc.networking.packets.client.KeepActivePacket
import com.zeriamc.networking.packets.client.KeepAlivePacket
import com.zeriamc.networking.packets.client.RequestHandshakePacket
import com.zeriamc.networking.packets.client.StartSessionRequestPacket
import com.zeriamc.networking.packets.client.game.PlayerJoinPacket
import com.zeriamc.networking.packets.client.game.PlayerQuitPacket
import com.zeriamc.networking.packets.client.proxy.*
import com.zeriamc.networking.packets.server.HandshakeResponsePacket
import com.zeriamc.networking.packets.server.StartSessionResponsePacket
import com.zeriamc.networking.packets.server.proxy.AddServerResponsePacket
import com.zeriamc.networking.packets.server.proxy.AntiCheatTriggerPacket
import com.zeriamc.networking.packets.server.proxy.ChatMessagePacket
import kotlin.properties.Delegates

object PacketCollector {
    var packets by Delegates.notNull<Collection<Class<out Packet>>>()
        private set

    private fun collectPackets() {
        packets = listOf(
            KeepActivePacket::class.java,
            KeepAlivePacket::class.java,
            RequestHandshakePacket::class.java,
            StartSessionRequestPacket::class.java,
            StartSessionResponsePacket::class.java,
            RequestHandshakePacket::class.java,
            HandshakeResponsePacket::class.java,
            PlayerJoinPacket::class.java,
            PlayerQuitPacket::class.java,
            AddServerPacket::class.java,
            ServerStartedPacket::class.java,
            ServerStoppedPacket::class.java,
            StartProxyPacket::class.java,
            StopProxyPacket::class.java,
            AddServerResponsePacket::class.java,
            AntiCheatTriggerPacket::class.java,
            ChatMessagePacket::class.java
        ).sortedBy { it.name }

        SocketLogger.logger.info("Collected ${packets.size} packets: " + packets.joinToString { it.simpleName })
    }

    fun EndPoint.registerPackets() {
        collectPackets()
        packets.forEach { kryo.register(it) }
        kryo.register(Array<String>::class.java)
    }
}