package com.zeriamc.networking.packets.client.proxy

import com.zeriamc.networking.packets.Packet

class AddServerPacket @JvmOverloads constructor(
    val proxyId: String? = null,
    val serverName: String? = null,
    val serverIp: String? = null,
    val maxPlayer: Int? = null
) : Packet