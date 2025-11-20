package com.hackheros.config


import com.hackheros.routes.api.apiAuthRoutes
import com.hackheros.routes.api.apiCategoryRoutes
import com.hackheros.routes.api.apiEducationMaterialsRoutes
import com.hackheros.routes.api.apiProgressRoutes
import com.hackheros.routes.api.apiQuestionRoutes
import com.hackheros.routes.api.apiUserRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
            get("/api/status") {
                call.respondText("OK")
            }

    }

    apiAuthRoutes()
    apiCategoryRoutes()
    apiEducationMaterialsRoutes()
    apiQuestionRoutes()
    apiProgressRoutes()
    apiUserRoutes()
}
