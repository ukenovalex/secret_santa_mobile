package auth

import auth.models.LoginRequest
import auth.models.LoginResponse

interface AuthRepository {
    suspend fun login(request: LoginRequest) : LoginResponse
    suspend fun validate() : Boolean
    fun saveToken(token: String)
    fun logout()
}