package santa.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import santa.models.FetchGiftedResponse

class KtorSantaRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun fetchGiftedUser(): FetchGiftedResponse {
        try {
            val response = httpClient.get {
                url {
                    path("santa/donee")
                }
            }
            if (response.status.isSuccess()) {
                return response.body()
            }
            throw RuntimeException("KtorSantaRemoteDataSource: Fetch Donee Invalid. Response: $response")
        } catch (e: Exception) {
            throw RuntimeException("KtorSantaRemoteDataSource: Server Error")
        }
    }

    suspend fun becomeSanta(): FetchGiftedResponse {
        try {
            val response = httpClient.post {
                url {
                    path("santa/become")
                }
            }
            if (response.status.isSuccess()) {
                return response.body()
            }
            throw RuntimeException("KtorSantaRemoteDataSource: Become Santa Invalid. Response: $response")
        } catch (e: Exception) {
            throw RuntimeException("KtorSantaRemoteDataSource: Server Error")
        }
    }

}