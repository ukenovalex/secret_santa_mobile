package auth

import auth.ktor.KtorAuthRemoteDataSource
import auth.models.LoginRequest
import auth.models.RegisterRequest

class AuthRepositoryImpl(
    val ktorAuthRemoteDataSource: KtorAuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(request: LoginRequest) {
        ktorAuthRemoteDataSource.login(request)
    }

    override suspend fun register(request: RegisterRequest) {
        ktorAuthRemoteDataSource.register(request)
    }

}