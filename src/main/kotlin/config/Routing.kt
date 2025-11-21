package com.hackheros.config


import com.hackheros.routes.admin.adminRoutes
import com.hackheros.routes.api.apiRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
            get("/api/status") {
                call.respondText("OK")
            }

            get("/api/ping") {
                call.respond(mapOf("pong" to true))
            }

        get("/api/db") {
            try {
                val result = com.hackheros.database.DatabaseFactory.dbQuery { 1 }
                call.respond(mapOf("db" to "connected"))
            } catch (e: Exception) {
                call.respond(mapOf("db" to "error", "message" to e.message))
            }
        }

    }

   apiRoutes()
    adminRoutes()

}
