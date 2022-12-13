package register

import register.ktor.KtorRegisterRemoteDataSource
import register.models.RegisterRequest
import register.models.RegisterResponse

class RegisterRepositoryImpl(
    private val ktorRegisterRemoteDataSource: KtorRegisterRemoteDataSource
) : RegisterRepository {
    override suspend fun fetchRegister(request: RegisterRequest): RegisterResponse {
        return ktorRegisterRemoteDataSource.fetchRegister(request)
    }
}