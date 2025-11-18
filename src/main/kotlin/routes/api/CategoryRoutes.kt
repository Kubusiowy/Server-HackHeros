package com.hackheros.routes.api

import com.hackheros.domain.repository.CategoryRepository
import com.hackheros.domain.repository.LessonRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.apiCategoryRoutes(){
    val Categoryrepo = CategoryRepository()
    val LessonRepo = LessonRepository()

    routing {
        route("/api/categories") {
            get {
                val categories = Categoryrepo.getAllCategories()
                call.respond(categories)
            }

            get("/{id}/lessons"){
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                    mapOf("error" to "invalid category id parameter"))

                val lessons = LessonRepo.getAllLessonsForCateogry(id)
                call.respond(lessons)
            }
        }
    }
}