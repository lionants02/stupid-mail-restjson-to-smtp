package thanachai.nstda.th

interface AppEnv {
    val sendMailMillisecondsTimeout: Long
    val apiKey: String

    // https://www.baeldung.com/java-email
    val smtpHost: String
    val smtpPort: Int
    val smtpEmail: String
    val smtpAuth: Boolean
    val smtpEnableTls: Boolean
    val smtpSSLTrust: String
    val smtpUsername: String
    val smtpPassword: String
}