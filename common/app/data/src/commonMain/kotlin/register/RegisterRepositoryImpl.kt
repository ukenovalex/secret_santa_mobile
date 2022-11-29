package register

import register.ktor.KtorRegisterRemoteDataSource
import register.models.RegisterRequest

class RegisterRepositoryImpl(
    private val ktorRegisterRemoteDataSource: KtorRegisterRemoteDataSource
) : RegisterRepository {
    override suspend fun fetchRegister(request: RegisterRequest) {
        ktorRegisterRemoteDataSource.fetchRegister(request)
    }
}