package thanachai.nstda.th.mail

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import thanachai.nstda.th.config
import thanachai.nstda.th.getLogger
import thanachai.nstda.th.mail.MailController.MailData

private val log by lazy { getLogger("configMailRouting") }
private val mailController by lazy { MailController() }
fun Application.configMailRouting() {
    routing {
        post("/mail") {
            require(call.request.header("api-key") == config.apiKey) {
                printLog(call)
                "API KEY Not Allow"
            }
            printLog(call)
            val requestBody = call.receive<MailData>()
            mailController.send(requestBody)
            call.respond(HttpStatusCode.Created)
        }
    }
}

private fun printLog(call: ApplicationCall) {
    val header = call.request.headers.toMap().toMutableMap()
    header.remove("api-key")
    log.info("Http header log $header")
}