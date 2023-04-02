package com.zeriamc.networking.server.utils

import com.esotericsoftware.kryonet.Connection

object RateLimiter {
    private val rateLimits = mutableMapOf<String, RateLimit>()

    fun isExpired(id: String): Boolean {
        val item = rateLimits[id] ?: return true
        return item.lastHit + item.delay < System.currentTimeMillis()
    }

    fun hit(id: String) {
        rateLimits[id]?.lastHit = System.currentTimeMillis()
    }

    fun create(id: String, delay: Long) = rateLimits.put(id, RateLimit(delay, 0L))

    fun createAndHit(id: String, delay: Long) {
        create(id, delay)
        hit(id)
    }

    fun consume(id: String, delay: Long): Boolean = if (isExpired(id)) {
        createAndHit(id, delay)
        true
    } else false

    fun consume(id: String, delay: Long, block: () -> Unit) {
        if (consume(id, delay)) {
            block()
        }
    }

    fun Connection.consumeRateLimit(id: String, delay: Long, block: () -> Unit) =
        consume("$this-$id", delay, block)
}