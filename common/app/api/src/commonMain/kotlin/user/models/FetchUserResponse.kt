package user.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchUserResponse(
    val id: Int,
    val email: String,
    val name: String,
    val isSanta: Boolean,
    val wishes: List<UserWish>
)
