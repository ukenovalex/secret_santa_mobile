package ktor

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

internal actual class HttpEngineFactory actual constructor() {
    actual fun createFactory(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}