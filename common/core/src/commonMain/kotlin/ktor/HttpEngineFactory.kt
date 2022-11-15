package ktor

import io.ktor.client.engine.*

internal expect class HttpEngineFactory constructor() {
    fun createFactory(): HttpClientEngineFactory<HttpClientEngineConfig>
}