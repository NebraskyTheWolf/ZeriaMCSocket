package com.zeriamc.networking.packets.server.proxy

import com.zeriamc.networking.packets.Packet

class AddServerResponsePacket @JvmOverloads constructor(
    val success: Boolean? = null
) : Packet