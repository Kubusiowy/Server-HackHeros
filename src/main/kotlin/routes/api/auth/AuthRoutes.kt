package com.hackheros.routes.api

import at.favre.lib.crypto.bcrypt.BCrypt
import com.hackheros.config.JwtConfig
import com.hackheros.domain.model.AuthResponse
import com.hackheros.domain.model.LoginRequest
import com.hackheros.domain.model.RegisterRequest
import com.hackheros.domain.repository.UserRepository
import com.hackheros.domain.repository.toUserResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.apiAuthRoutes() {

    val userRepo = UserRepository()

    routing {
        route("/api/auth") {


            post("/register") {
                val body = call.receive<RegisterRequest>()

                val existing = userRepo.findByEmail(body.email)
                if (existing != null) {
                    return@post call.respond(
                        HttpStatusCode.Conflict,
                        mapOf("error" to "User with this email already exists")
                    )
                }

                val user = userRepo.createUser(body)
                val token = JwtConfig.generateToken(user.id, user.username)

                val response = AuthResponse(
                    user = user.toUserResponse(),
                    token = token
                )

                call.respond(HttpStatusCode.Created, response)
            }


            post("/login") {
                val body = call.receive<LoginRequest>()
                println("LOGIN: email=${body.email}")

                val user = userRepo.findByEmail(body.email.trim())
                println("LOGIN: user from DB = $user")

                if (user == null) {
                    println("LOGIN: user NOT FOUND")
                    return@post call.respond(
                        HttpStatusCode.Unauthorized,
                        mapOf("error" to "Invalid email or password")
                    )
                }

                val result = BCrypt.verifyer().verify(
                    body.password.toCharArray(),
                    user.hashPass.toCharArray()
                )
                println("LOGIN: password verified = ${result.verified}")

                if (!result.verified) {
                    return@post call.respond(
                        HttpStatusCode.Unauthorized,
                        mapOf("error" to "Invalid email or password")
                    )
                }

                val token = JwtConfig.generateToken(user.id, user.username)

                val response = AuthResponse(
                    user = user.toUserResponse(),
                    token = token
                )

                call.respond(HttpStatusCode.OK, response)
            }


            authenticate("auth-jwt") {
                get("/me") {

                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal?.payload?.getClaim("userId")?.asInt()

                    if (userId == null) {
                        return@get call.respond(
                            HttpStatusCode.Unauthorized,
                            mapOf("error" to "Invalid token")
                        )
                    }

                    val user = userRepo.findById(userId)
                        ?: return@get call.respond(
                            HttpStatusCode.NotFound,
                            mapOf("error" to "User not found")
                        )

                    call.respond(user.toUserResponse())
                }
            }
        }
    }
}
