package auth

import auth.models.LoginRequest
import auth.models.LoginResponse
import auth.models.RegisterRequest
import auth.models.RegisterResponse

interface AuthRepository {
    suspend fun login(request: LoginRequest) : LoginResponse
    suspend fun register(request: RegisterRequest) : RegisterResponse
    suspend fun validate() : Boolean
    fun saveToken(token: String)
    fun logout()
}