package com.zeriamc.networking.packets.client.proxy

import com.zeriamc.networking.packets.Packet
import java.util.UUID

class ServerStartedPacket @JvmOverloads constructor(
    val proxyId: String? = null,
    val identifier: String? = null,
    val host: String? = null,
    val map: String? = null,
    val template: String? = null,
    val maxPlayer: String? = null,
    val queuedPlayers: Array<UUID>? = null
) : Packet