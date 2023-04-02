package com.zeriamc.networking.packets.client

import com.zeriamc.networking.packets.Packet

class StartSessionRequestPacket @JvmOverloads constructor(
    val uuid: String? = null
) : Packet {
    companion object {
        const val serialVersionUID = 32L
    }
}