package user.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import user.models.FetchUserResponse

class KtorUserRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun fetch(): FetchUserResponse {
        val response = httpClient.get {
            url {
                path("user/info")
            }
        }
        if (response.status.isSuccess()) {
            return response.body()
        }
        throw RuntimeException("KtorUserRemoteDataSource: Fetch User Invalid. Response: $response")
    }
}