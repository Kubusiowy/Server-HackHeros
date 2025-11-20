package com.hackheros.config

import io.ktor.server.application.*
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond

fun Application.configureSecurity() {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = JwtConfig.realm

            verifier(JwtConfig.verifier)

            validate { credential ->
                val userId = credential.payload.getClaim("userId").asInt()
                if (userId != null) {

                    JWTPrincipal(credential.payload)
                } else {

                    null
                }
            }

            challenge { _, _ ->

                call.respond(
                    io.ktor.http.HttpStatusCode.Unauthorized,
                    mapOf("error" to "Token is not valid or has expired")
                )
            }
        }
    }
}
