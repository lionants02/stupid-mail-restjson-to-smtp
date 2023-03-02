package thanachai.nstda.th

class AppEnvBySystemEmv() : AppEnv {
    override val apiKey: String
        get() = "API_KEY".systemEnv
    override val smtpHost: String
        get() = "SMTP_SERVER".systemEnv

    override val sendMailMillisecondsTimeout: Long
        get() = "MAIL_MILLISECONDS_TIMEOUT".systemEnv.toLong()
    override val smtpPort: Int
        get() = "SMTP_PORT".systemEnv.toInt()
    override val smtpEmail: String
        get() = "SMTP_EMAIL".systemEnv
    override val smtpAuth: Boolean
        get() = "SMTP_ENABLE_AUTH".systemEnv.toBoolean()
    override val smtpEnableTls: Boolean
        get() = "SMTP_ENABLE_TLS".systemEnv.toBoolean()
    override val smtpSSLTrust: String
        get() = "SMTP_SSL_TRUST".systemEnv
    override val smtpUsername: String
        get() = "SMTP_USERNAME".systemEnv
    override val smtpPassword: String
        get() = "SMTP_PASSWORD".systemEnv
    private val String.systemEnv: String
        get() {
            val value = System.getenv(this) ?: ""
            log.info("Get Env $this = $value")
            return value
        }

    companion object {
        private val log by lazy { getLogger(AppEnvBySystemEmv::class.java) }
    }
}