package com.zeriamc.networking.packets.server.proxy

import com.zeriamc.networking.packets.Packet
import java.util.UUID

class ChatMessagePacket @JvmOverloads constructor(
    val proxyId: String? = null,
    val lang: String? = null,
    val identifier: String? = null,
    val sender: UUID? = null,
    val message: String? = null
) : Packet