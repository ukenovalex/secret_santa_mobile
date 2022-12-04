package santa

import santa.models.FetchGiftedResponse

interface SantaRepository {
    suspend fun fetchGiftedUser(): FetchGiftedResponse
    suspend fun becomeSanta(): FetchGiftedResponse
}