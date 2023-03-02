package thanachai.nstda.th.mail

import kotlinx.serialization.Serializable
import thanachai.nstda.th.config
import thanachai.nstda.th.getLogger

class MailController {
    fun send(mailData: MailData) {
        log.info("Subject ${mailData.subject} Send to ${mailData.to}")
        mail.send(mailData.subject, mailData.to, mailData.body)
    }

    @Serializable
    data class MailData(val subject: String, val to: String, val body: String)
    companion object {
        private val mail: SendMail by lazy { SendMailBySMTP(config) }
        private val log by lazy { getLogger(MailController::class.java) }
    }
}