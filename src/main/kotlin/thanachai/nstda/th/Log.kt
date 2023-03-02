package thanachai.nstda.th

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T> getLogger(clazz: Class<T>): Logger {
    return LoggerFactory.getLogger(clazz)
}

fun getLogger(clazz: String): Logger {
    return LoggerFactory.getLogger(clazz)
}