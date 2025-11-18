package com.hackheros.config


import com.hackheros.routes.api.apiCategoryRoutes
import com.hackheros.routes.api.apiEducationMaterialsRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        get("/api/") {
            call.respondText("OK")
        }
    }

    apiCategoryRoutes()

    apiEducationMaterialsRoutes()
}
