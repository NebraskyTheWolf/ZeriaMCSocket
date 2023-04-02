package com.zeriamc.networking.packets.server

import com.zeriamc.networking.packets.Packet

class HandshakeResponsePacket @JvmOverloads constructor(
    val success: Boolean? = null,
    val code: Int? = null
) : Packet