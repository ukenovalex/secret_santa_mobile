package auth

import auth.models.LoginRequest
import auth.models.RegisterRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest)
    suspend fun register(request: RegisterRequest)
}