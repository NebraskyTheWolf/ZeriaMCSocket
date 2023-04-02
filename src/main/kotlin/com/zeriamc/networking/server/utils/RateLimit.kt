package com.zeriamc.networking.server.utils

class RateLimit(
    val delay: Long,
    var lastHit: Long
)