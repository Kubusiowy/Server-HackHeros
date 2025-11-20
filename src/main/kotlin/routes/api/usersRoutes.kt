package com.hackheros.routes.api

import com.hackheros.domain.model.UpdatePreferencesRequest
import com.hackheros.domain.repository.UserRepository
import com.hackheros.domain.repository.toUserResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.apiUserRoutes() {

    val userRepo = UserRepository()

    routing {
        authenticate("auth-jwt") {
            route("/api/user") {

                // PATCH /api/user/preferences
                patch("/preferences") {
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal?.payload?.getClaim("userId")?.asInt()

                    if (userId == null) {
                        return@patch call.respond(
                            HttpStatusCode.Unauthorized,
                            mapOf("error" to "Invalid token")
                        )
                    }

                    val body = call.receive<UpdatePreferencesRequest>()

                    val updated = userRepo.updatePreferences(userId, body)
                        ?: return@patch call.respond(
                            HttpStatusCode.NotFound,
                            mapOf("error" to "User not found")
                        )

                    call.respond(updated.toUserResponse())
                }
            }
        }
    }
}
