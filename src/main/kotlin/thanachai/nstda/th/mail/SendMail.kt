package thanachai.nstda.th.mail

interface SendMail {
    fun send(subject: String, toEmail: String, bodyMessage: String)
}