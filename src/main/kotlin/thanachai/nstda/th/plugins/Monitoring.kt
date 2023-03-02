package thanachai.nstda.th.plugins

import io.ktor.server.metrics.dropwizard.*
import com.codahale.metrics.*
import io.micrometer.prometheus.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

//fun Application.configureMonitoring() {
//    install(DropwizardMetrics) {
//      Slf4jReporter.forRegistry(registry)
//        .outputTo(this@module.log)
//        .convertRatesTo(TimeUnit.SECONDS)
//        .convertDurationsTo(TimeUnit.MILLISECONDS)
//        .build()
//        .start(10, TimeUnit.SECONDS)
//    }
//    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)
//
//        install(MicrometerMetrics) {
//            registry = appMicrometerRegistry
//            // ...
//        }
//    routing {
//        get("/metrics-micrometer") {
//            call.respond(appMicrometerRegistry.scrape())
//        }
//    }
//}
