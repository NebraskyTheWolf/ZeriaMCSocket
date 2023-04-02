package com.zeriamc.networking.packets.client.game

import com.zeriamc.networking.packets.Packet
import java.util.UUID

class PlayerJoinPacket @JvmOverloads constructor(
    val identifier: String? = null,
    val username: String? = null,
    val uuid: UUID? = null
): Packet