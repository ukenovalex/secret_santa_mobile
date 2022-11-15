package auth.ktor

import auth.models.LoginRequest
import auth.models.RegisterRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorAuthRemoteDataSource(val httpClient: HttpClient) {
    suspend fun login(loginRequest: LoginRequest) {
        return httpClient.post {
            url {
                path("user/login")
                setBody(loginRequest)
            }
        }.body()
    }
    suspend fun register(registerRequest: RegisterRequest) {
        return httpClient.post {
            url {
                path("user/register")
                setBody(registerRequest)
            }
        }.body()
    }
}