package register.ktor


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import register.models.RegisterRequest

class KtorRegisterRemoteDataSource(val httpClient: HttpClient) {
    suspend fun fetchRegister(request: RegisterRequest) {
        try {
            val response = httpClient.post {
                url {
                    path("user/register")
                    setBody(request)
                }
            }
            if (response.status.isSuccess()) {
                return response.body()
            }
            throw RuntimeException("KtorRegisterRemoteDataSource: Register User Invalid. Response: $response")
        } catch (e: RuntimeException) {
            throw RuntimeException("KtorRegisterRemoteDataSource: Server Error: Status code: 0")
        }
    }
}