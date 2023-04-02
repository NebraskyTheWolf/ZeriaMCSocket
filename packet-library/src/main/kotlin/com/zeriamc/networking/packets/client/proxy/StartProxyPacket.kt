package com.zeriamc.networking.packets.client.proxy

import com.zeriamc.networking.packets.Packet

class StartProxyPacket @JvmOverloads constructor(
    val ip: String? = null,
    val regionId: String? = null,
    val proxyId: String? = null
) : Packet