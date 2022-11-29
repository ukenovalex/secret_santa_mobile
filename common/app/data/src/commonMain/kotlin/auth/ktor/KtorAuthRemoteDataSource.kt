package auth.ktor

import auth.models.LoginRequest
import auth.models.LoginResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorAuthRemoteDataSource(val httpClient: HttpClient) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        try {
            val response = httpClient.post {
                url {
                    path("user/login")
                    setBody(loginRequest)
                }
            }
            if (response.status.isSuccess()) {
                return response.body()
            }
            throw RuntimeException("Login invalid")
        } catch (e: Exception) {
            throw RuntimeException("Server Error")
        }
    }

    suspend fun validate(): Boolean {
        try {
            val response = httpClient.get {
                url {
                    path("user/info")
                }
            }
            if (response.status.isSuccess()) {
                return true
            }
            return false
        } catch(e: RuntimeException) {
            return false
        }
    }
}