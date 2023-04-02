package com.zeriamc.networking.packets.client.game

import com.zeriamc.networking.packets.Packet
import java.util.*

class PlayerQuitPacket @JvmOverloads constructor(
    val identifier: String? = null,
    val uuid: UUID? = null
): Packet