package auth

import auth.ktor.KtorAuthRemoteDataSource
import auth.kvalut.KVaultAuthRemoteDataSource
import auth.models.LoginRequest
import auth.models.LoginResponse

class AuthRepositoryImpl(
    private val ktorAuthRemoteDataSource: KtorAuthRemoteDataSource,
    private val kVaultAuthRemoteDataSource: KVaultAuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return ktorAuthRemoteDataSource.login(request)
    }

    override fun saveToken(token: String) {
        kVaultAuthRemoteDataSource.saveToken(token)
    }

    override suspend fun validate(): Boolean {
        if (!kVaultAuthRemoteDataSource.isTokenExist) {
            return false
        }
        return ktorAuthRemoteDataSource.validate()
    }

    override fun logout() {
        kVaultAuthRemoteDataSource.clear()
    }
}