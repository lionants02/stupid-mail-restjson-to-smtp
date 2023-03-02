package thanachai.nstda.th

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import thanachai.nstda.th.mail.configMailRouting
import thanachai.nstda.th.plugins.*

val config: AppEnv by lazy { AppEnvBySystemEmv() }
fun main() {
    getLogger("Main").info("Run with java version ${System.getProperty("java.version")}")
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    configureAdministration()
//    configureMonitoring()
    configureSerialization()
    configureHTTP()
    configureRouting()
    configMailRouting()
}
