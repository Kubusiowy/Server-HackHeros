package com.hackheros.routes.admin

import com.hackheros.domain.model.CategoryRequest
import com.hackheros.domain.model.EducationMaterialRequest
import com.hackheros.domain.model.LessonRequest
import com.hackheros.domain.model.ParagraphRequest
import com.hackheros.domain.model.QuestionRequest
import com.hackheros.domain.repository.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.adminRoutes() {

    val categoryRepo = CategoryRepository()
    val lessonRepo = LessonRepository()
    val questionRepo = QuestionRepository()
    val materialsRepo = EducationMaterialsRepository()

    routing {

        // DODAJ KATEGORIĘ
        post("/admin/categories") {
            val body = call.receive<CategoryRequest>()
            categoryRepo.addCategory(body)
            call.respond(HttpStatusCode.Created, mapOf("message" to "Category created"))
        }

        // DODAJ LEKCJĘ DO KATEGORII
        post("/admin/categories/{id}/lessons") {
            val categoryId = call.parameters["id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Invalid category id")

            val body = call.receive<LessonRequest>()
            lessonRepo.addForCategory(body, categoryId)
            call.respond(HttpStatusCode.Created, mapOf("message" to "Lesson created"))
        }

        // DODAJ PYTANIE DO LEKCJI
        post("/admin/lessons/{id}/questions") {
            val lessonId = call.parameters["id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Invalid lesson id")

            val body = call.receive<QuestionRequest>()
            questionRepo.addForLesson(body, lessonId)
            call.respond(HttpStatusCode.Created, mapOf("message" to "Question created"))
        }

        // DODAJ MATERIAŁ DO LEKCJI
        post("/admin/lessons/{id}/materials") {
            val lessonId = call.parameters["id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Invalid lesson id")

            val body = call.receive<EducationMaterialRequest>()
            materialsRepo.addMaterialForLesson(body, lessonId)
            call.respond(HttpStatusCode.Created, mapOf("message" to "Material created"))
        }

        // DODAJ PARAGRAF DO MATERIAŁU
        post("/admin/materials/{id}/paragraphs") {
            val materialId = call.parameters["id"]?.toIntOrNull()
                ?: return@post call.respond(HttpStatusCode.BadRequest, "Invalid material id")

            val body = call.receive<ParagraphRequest>()
            materialsRepo.addParagraphForMaterial(body, materialId)
            call.respond(HttpStatusCode.Created, mapOf("message" to "Paragraph created"))
        }
    }
}