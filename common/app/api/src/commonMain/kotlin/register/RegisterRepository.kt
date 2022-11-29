package register

import register.models.RegisterRequest

interface RegisterRepository {
    suspend fun fetchRegister(request: RegisterRequest)
}