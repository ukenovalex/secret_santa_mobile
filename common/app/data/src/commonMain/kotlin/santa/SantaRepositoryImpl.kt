package santa

import santa.ktor.KtorSantaRemoteDataSource
import santa.models.FetchGiftedResponse

class SantaRepositoryImpl(
    private val ktorSantaRemoteDataSource: KtorSantaRemoteDataSource
) : SantaRepository {
    override suspend fun fetchGiftedUser(): FetchGiftedResponse {
        return ktorSantaRemoteDataSource.fetchGiftedUser()
    }

    override suspend fun becomeSanta(): FetchGiftedResponse {
        return ktorSantaRemoteDataSource.becomeSanta()
    }
}