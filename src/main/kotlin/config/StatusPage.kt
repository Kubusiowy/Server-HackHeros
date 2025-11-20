package com.hackheros.config

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.exceptions.ExposedSQLException

@Serializable
data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)

fun Application.configureStatusPages() {
    install(StatusPages) {


        status(HttpStatusCode.BadRequest) { call, status ->
            call.respond(
                status,
                ErrorResponse(
                    status = status.value,
                    error = "Bad Request",
                    message = "The request was malformed or missing required data."
                )
            )
        }


        status(HttpStatusCode.Unauthorized) { call, status ->
            call.respond(
                status,
                ErrorResponse(
                    status = status.value,
                    error = "Unauthorized",
                    message = "You must be logged in to access this resource."
                )
            )
        }


        status(HttpStatusCode.Forbidden) { call, status ->
            call.respond(
                status,
                ErrorResponse(
                    status = status.value,
                    error = "Forbidden",
                    message = "You do not have permission to access this resource."
                )
            )
        }


        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(
                status,
                ErrorResponse(
                    status = status.value,
                    error = "Not Found",
                    message = "The requested resource was not found."
                )
            )
        }


        exception<ExposedSQLException> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse(
                    status = HttpStatusCode.InternalServerError.value,
                    error = "Database Error",
                    message = cause.message ?: "Unknown database error"
                )
            )
        }


        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse(
                    status = HttpStatusCode.InternalServerError.value,
                    error = "Internal Server Error",
                    message = cause.message ?: "Unexpected server error"
                )
            )
        }
    }
}