package user

import user.ktor.KtorUserRemoteDataSource
import user.models.*

class UserRepositoryImpl(
    private val ktorUserRemoteDataSource: KtorUserRemoteDataSource
) : UserRepository {
    override suspend fun fetchUserInfo(): FetchUserResponse {
        return ktorUserRemoteDataSource.fetch()
    }

    override suspend fun addWish(request: AddWishRequest): AddWishResponse {
        return ktorUserRemoteDataSource.addWish(request)
    }

    override suspend fun removeWish(request: RemoveWishRequest) {
        return ktorUserRemoteDataSource.removeWish(request)
    }
}