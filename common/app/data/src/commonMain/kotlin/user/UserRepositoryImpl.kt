package user

import user.ktor.KtorUserRemoteDataSource
import user.models.FetchUserResponse

class UserRepositoryImpl(
    private val ktorUserRemoteDataSource: KtorUserRemoteDataSource
) : UserRepository {
    override suspend fun fetchUserInfo(): FetchUserResponse {
        return ktorUserRemoteDataSource.fetch()
    }
}