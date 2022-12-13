package register

import register.models.RegisterRequest
import register.models.RegisterResponse

interface RegisterRepository {
    suspend fun fetchRegister(request: RegisterRequest) : RegisterResponse
}