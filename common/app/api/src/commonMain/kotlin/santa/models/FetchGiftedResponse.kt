package santa.models
import kotlinx.serialization.Serializable
import user.models.UserWish

@Serializable
data class FetchGiftedResponse(
    val name: String,
    val wishes: List<UserWish>
)
