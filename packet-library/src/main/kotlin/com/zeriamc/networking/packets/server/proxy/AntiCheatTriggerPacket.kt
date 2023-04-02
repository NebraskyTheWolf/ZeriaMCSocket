package com.zeriamc.networking.packets.server.proxy

import com.zeriamc.networking.packets.Packet
import java.util.UUID

class AntiCheatTriggerPacket @JvmOverloads constructor(
    val identifier: String? = null,
    val operator: String? = null,
    val ratio: Float? = null,
    val playerId: UUID? = null
) : Packet