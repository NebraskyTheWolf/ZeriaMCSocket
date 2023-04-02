package com.zeriamc.networking.packets.client.proxy

import com.zeriamc.networking.packets.Packet

class StopProxyPacket @JvmOverloads constructor(
    val proxyId: String? = null
) : Packet