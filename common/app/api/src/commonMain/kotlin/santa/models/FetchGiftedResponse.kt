package santa.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchGiftedResponse(
    val name: String,
    val wishes: List<String>
)
