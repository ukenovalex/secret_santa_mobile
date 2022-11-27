package user

import user.models.FetchUserResponse

interface UserRepository {
    suspend fun fetchUserInfo() : FetchUserResponse
}