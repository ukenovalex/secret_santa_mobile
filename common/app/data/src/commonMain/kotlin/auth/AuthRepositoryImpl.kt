package auth

import auth.ktor.KtorAuthRemoteDataSource
import auth.kvalut.KVaultAuthRemoteDataSource
import auth.models.LoginRequest
import auth.models.LoginResponse
import auth.models.RegisterRequest
import auth.models.RegisterResponse

class AuthRepositoryImpl(
    private val ktorAuthRemoteDataSource: KtorAuthRemoteDataSource,
    private val kVaultAuthRemoteDataSource: KVaultAuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return ktorAuthRemoteDataSource.login(request)
    }

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        return ktorAuthRemoteDataSource.register(request)
    }

    override fun saveToken(token: String) {
        kVaultAuthRemoteDataSource.saveToken(token)
    }
}