import com.liftric.kvault.KVault
import configuration.BaseURL
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import ktor.HttpEngineFactory
import kvault.KEYS.AUTH_TOKEN
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val ktorModule = DI.Module("ktorModule") {

    bind<HttpClient>() with singleton {
        val store: KVault = instance()
        HttpClient(HttpEngineFactory().createFactory()) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            install(DefaultRequest)

            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 15000
                requestTimeoutMillis = 30000
            }

            defaultRequest {
                val token = store.string(AUTH_TOKEN)
                url(BaseURL)
                header("Content-Type", "application/json; charset=UTF-8")
                if (token != null) {
                    header("Authorization", "Bearer $token")
                }
            }
        }.apply {
            plugin(HttpSend).intercept { request ->
                val originalCall = execute(request)
                if (originalCall.response.status.value == HttpStatusCode.Unauthorized.value) {
                    store.clear()
                    execute(request)
                } else {
                    originalCall
                }
            }
        }
    }

}
