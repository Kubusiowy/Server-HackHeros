package com.hackheros.routes.api

import com.hackheros.domain.model.CompleteLessonRequest
import com.hackheros.domain.repository.ProgressRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.apiProgressRoutes() {

    val progressRepo = ProgressRepository()

    routing {
        authenticate("auth-jwt") {
            route("/api/progress") {


                post("/complete-lesson") {
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal?.payload?.getClaim("userId")?.asInt()

                    if (userId == null) {
                        return@post call.respond(
                            HttpStatusCode.Unauthorized,
                            mapOf("error" to "Invalid token")
                        )
                    }

                    val body = call.receive<CompleteLessonRequest>()

                    val saved = progressRepo.saveCompletedLesson(userId, body)

                    call.respond(HttpStatusCode.Created, saved)
                }


                get {
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal?.payload?.getClaim("userId")?.asInt()

                    if (userId == null) {
                        return@get call.respond(
                            HttpStatusCode.Unauthorized,
                            mapOf("error" to "Invalid token")
                        )
                    }

                    val progress = progressRepo.getUserProgress(userId)
                    call.respond(progress)
                }
            }
        }
    }
}
