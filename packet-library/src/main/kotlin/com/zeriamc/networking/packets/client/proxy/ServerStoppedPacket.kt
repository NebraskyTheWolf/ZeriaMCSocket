package com.zeriamc.networking.packets.client.proxy

import com.zeriamc.networking.packets.Packet

class ServerStoppedPacket @JvmOverloads constructor(
    val proxyId: String? = null,
    val identifier: String? = null,
    val message: String? = null
) : Packet