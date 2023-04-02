package com.zeriamc.networking.server.session

import com.esotericsoftware.kryonet.Connection

data class Session(
    val connection: Connection,
    val uuid: String
) {
    val createdAt: Long = System.currentTimeMillis()
    val metadata = mutableMapOf<String, Any?>()

    override fun toString(): String {
        return "Session(connection=$connection, jwt=$uuid, createdAt=$createdAt, metadata=$metadata)"
    }
}
