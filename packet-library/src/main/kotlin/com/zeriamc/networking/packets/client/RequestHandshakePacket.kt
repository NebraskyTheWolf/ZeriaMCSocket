package com.zeriamc.networking.packets.client

import com.zeriamc.networking.packets.Packet

class RequestHandshakePacket @JvmOverloads constructor(
    val clientVersion: String? = null,
    val clientName:  String? = null,
    val clientMods: Array<String>? = null
) : Packet {
    companion object {
        const val serialVersionUID = 375L
    }
}