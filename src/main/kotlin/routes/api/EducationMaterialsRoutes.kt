package com.hackheros.routes.api

import com.hackheros.domain.repository.EducationMaterialsRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.apiEducationMaterialsRoutes() {

    val MaterialRepo = EducationMaterialsRepository()

    routing {

        route("api/education-materials") {
            get("/lesson/{id}"){
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                    mapOf("error" to "Missing or malformed id"))

                val materials = MaterialRepo.getAllEducationMaterialsForLesson(id)
                call.respond(materials)
            }

            get("/paragraphs/{id}")  {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                    mapOf("error" to "Missing or malformed id"))

                val paragraphs = MaterialRepo.getAllParagraphsForEducationMaterial(id)
                call.respond(paragraphs)
            }

        }

    }
}