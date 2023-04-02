package com.zeriamc.networking.packets.server

import com.zeriamc.networking.packets.Packet

class StartSessionResponsePacket @JvmOverloads constructor(
    val success: Boolean? = null
) : Packet