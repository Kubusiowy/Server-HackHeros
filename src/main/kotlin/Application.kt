package com.hackheros

import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureRouting()
}
