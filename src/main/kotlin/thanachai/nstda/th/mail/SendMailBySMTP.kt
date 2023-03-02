package thanachai.nstda.th.mail

import jakarta.mail.*
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeBodyPart
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import thanachai.nstda.th.AppEnv
import thanachai.nstda.th.getLogger
import java.util.*
import kotlin.concurrent.thread

class SendMailBySMTP(private val config: AppEnv) : SendMail {
    private var mailQueue: Int = 0
    private val lock: Any = Any()


    override fun send(subject: String, toEmail: String, bodyMessage: String) {
        try {
            synchronized(lock) {
                mailQueue++
                log.info("Mail queue init $mailQueue to $toEmail")
            }
            require(toEmail.isNotEmpty()) { "Email to empty" }
            thread(true) {
                runBlocking {
                    // timeout 5 Min
                    withTimeout(config.sendMailMillisecondsTimeout) {
                        try {
                            syncSend(toEmail, bodyMessage, subject)
                        } catch (ex: Exception) {
                            log.error(ex.message, ex)
                        }
                    }
                }

            }
        } catch (ex: Exception) {
            log.error(ex.message, ex)
        } finally {
            synchronized(lock) {
                mailQueue--
                log.info("Mail queue done $mailQueue")
            }
        }
    }

    @Synchronized
    private fun syncSend(subject: String, toEmail: String, bodyMessage: String) {

        // ถ้า smtpAuth เป็น true ให้ login ถ้าไม่ใช่ ไม่ต้อง login
        val session = if (config.smtpAuth) Session.getInstance(prop, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(config.smtpUsername, config.smtpPassword)
            }
        }) else Session.getInstance(prop)

        val message = MimeMessage(session)
        message.setFrom(InternetAddress(config.smtpEmail))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
        message.subject = subject

        val mimeBodyPart = MimeBodyPart()
        mimeBodyPart.setContent(bodyMessage, "text/html; charset=utf-8")
        val multipart = MimeMultipart()
        multipart.addBodyPart(mimeBodyPart)

        message.setContent(multipart)
        Transport.send(message)
    }

    /**
     *     // https://github.com/jakartaee/mail-api
     */
    private val prop by lazy {
        Properties().apply {
            this["mail.smtp.auth"] = config.smtpAuth
            this["mail.smtp.starttls.enable"] = config.smtpEnableTls
            this["mail.smtp.host"] = config.smtpHost
            this["mail.smtp.port"] = config.smtpPort
            this["mail.smtp.ssl.trust"] = config.smtpSSLTrust
        }
    }

    companion object {
        private val log by lazy { getLogger(SendMailBySMTP::class.java) }
    }
}