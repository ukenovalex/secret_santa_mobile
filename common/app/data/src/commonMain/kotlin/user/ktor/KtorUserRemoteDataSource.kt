package user.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import user.models.*

class KtorUserRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun fetch(): FetchUserResponse {
        try {
            val response = httpClient.get {
                url {
                    path("user/info")
                }
            }
            if (response.status.isSuccess()) {
                return response.body()
            }
            throw RuntimeException("KtorUserRemoteDataSource: Fetch User Invalid. Response: $response")
        } catch (e: RuntimeException) {
            throw RuntimeException("KtorUserRemoteDataSource: Server Error")
        }
    }

    suspend fun addWish(request: AddWishRequest): AddWishResponse {
        try {
            val response = httpClient.post {
                url {
                    path("wish")
                    setBody(request)
                }
            }
            if (response.status.isSuccess()) {
                return response.body()
            }
            throw RuntimeException("KtorUserRemoteDataSource: Add Wish Invalid. Response: $response")
        } catch (e: RuntimeException) {
            throw RuntimeException("KtorUserRemoteDataSource: Server Error")
        }
    }

    suspend fun removeWish(request: RemoveWishRequest) {
        try {
            val response = httpClient.delete {
                url {
                    path("wish")
                    setBody(request)
                }
            }
            if (response.status.isSuccess()) {
                return
            }
            throw RuntimeException("KtorUserRemoteDataSource: Delete Wish Invalid. Response: $response")
        } catch (e: RuntimeException) {
            throw RuntimeException("KtorUserRemoteDataSource: Server Error")
        }
    }

    suspend fun getUserList(): List<FetchUserListItemResponse> {
        try {
            val response = httpClient.get {
                url {
                    path("user/all")
                }
            }
            if (response.status.isSuccess()) {
                return response.body()
            }
            throw RuntimeException("KtorUserRemoteDataSource: Get User List Invalid. Response: $response")
        } catch (e: RuntimeException) {
            throw RuntimeException("KtorUserRemoteDataSource: Server Error")
        }
    }
}