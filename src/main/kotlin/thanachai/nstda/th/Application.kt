package thanachai.nstda.th

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import thanachai.nstda.th.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    configureAdministration()
//    configureMonitoring()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
