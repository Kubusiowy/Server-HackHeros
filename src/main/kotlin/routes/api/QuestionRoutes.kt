package com.hackheros.routes.api

import com.hackheros.domain.repository.QuestionRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.apiQuestionRoutes() {
    val questionRepo = QuestionRepository()

    routing {
        get("/api/questions/lesson/{id}"){
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                mapOf("error" to "invalid id"))

            val questions= questionRepo.getQuestionForLessons(id)
            call.respond(questions)
        }
    }

}