package user

import user.models.AddWishRequest
import user.models.AddWishResponse
import user.models.FetchUserResponse
import user.models.RemoveWishRequest

interface UserRepository {
    suspend fun fetchUserInfo() : FetchUserResponse
    suspend fun addWish(request: AddWishRequest) : AddWishResponse
    suspend fun removeWish(request: RemoveWishRequest)
}