package com.hackheros.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

object JwtConfig {

    private val secret = System.getenv("JWT_SECRET") ?: "dev-secret-change-me"
    private val algorithm = Algorithm.HMAC256(secret)

    const val issuer = "hackheros-server"
    const val audience = "hackheros-users"
    const val realm = "HackHeros"

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()

    fun generateToken(userId: Int, username: String): String {
        val now = System.currentTimeMillis()
        val expiresAt = Date(now + 1000L * 60 * 60 * 24) // 24h

        return JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("userId", userId)
            .withClaim("username", username)
            .withExpiresAt(expiresAt)
            .sign(algorithm)
    }
}