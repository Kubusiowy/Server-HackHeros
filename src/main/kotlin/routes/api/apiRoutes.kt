package com.hackheros.routes.api

import com.hackheros.domain.model.PassedLessonRequest
import com.hackheros.domain.repository.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.apiRoutes() {

    val categoryRepo = CategoryRepository()
    val lessonRepo = LessonRepository()
    val questionRepo = QuestionRepository()
    val materialsRepo = EducationMaterialsRepository()
    val passedRepo = PassedLessonRepository()

    routing {


        get("/api/categories") {
            call.respond(categoryRepo.getAll())
        }


        get("/api/categories/{id}/lessons") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid category id")

            call.respond(lessonRepo.getForCategory(id))
        }

        // Pytania danej lekcji
        get("/api/lessons/{id}/questions") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid lesson id")

            call.respond(questionRepo.getForLesson(id))
        }


        get("/api/lessons/{id}/materials") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid lesson id")

            call.respond(materialsRepo.getMaterialsForLesson(id))
        }


        get("/api/materials/{id}/paragraphs") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid material id")

            call.respond(materialsRepo.getParagraphsForMaterial(id))
        }

        // Zapis zaliczonej lekcji przez "stałego" usera
        post("/api/progress/lesson/{id}") {
            val lessonId = call.parameters["id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Invalid lesson id")

            val body = call.receive<PassedLessonRequest>()
            if (body.lessonId != lessonId) {
                return@post call.respond(HttpStatusCode.BadRequest, "lessonId mismatch")
            }

            passedRepo.addForDemoUser(body)
            call.respond(HttpStatusCode.Created, mapOf("message" to "Progress saved"))
        }

        // Historia zaliczonych lekcji tego usera
        get("/api/progress") {
            call.respond(passedRepo.getForDemoUser())
        }
    }
}
