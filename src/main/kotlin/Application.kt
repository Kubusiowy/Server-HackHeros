package com.hackheros

import com.hackheros.config.configureHTTP
import com.hackheros.config.configureRouting
import com.hackheros.config.configureSecurity
import com.hackheros.config.configureSerialization
import com.hackheros.config.configureStatusPages
import com.hackheros.database.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureStatusPages()
    configureRouting()


}
