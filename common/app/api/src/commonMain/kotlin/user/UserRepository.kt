package user

import user.models.*

interface UserRepository {
    suspend fun fetchUserInfo() : FetchUserResponse
    suspend fun fetchUserList() : List<FetchUserListItemResponse>
    suspend fun addWish(request: AddWishRequest) : AddWishResponse
    suspend fun removeWish(request: RemoveWishRequest)

}