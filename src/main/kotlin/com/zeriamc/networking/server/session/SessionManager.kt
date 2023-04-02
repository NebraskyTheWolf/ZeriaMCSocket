package com.zeriamc.networking.server.session

import com.esotericsoftware.kryonet.Connection
import com.zeriamc.networking.logger.SocketLogger

object SessionManager {
    private val sessions = mutableMapOf<Connection, Session>()

    fun buildSession(connection: Connection, uuid: String): Session {
        connection.setName(uuid)

        val session = Session(connection, uuid)
        sessions[connection] = session

        return session
    }

    fun findSession(connection: Connection) = sessions[connection]

    val Connection.session: Session
        get() = sessionOrNull ?: error("Socket connection has no associated session!")

    val Connection.sessionOrNull: Session?
        get() = findSession(this)
}