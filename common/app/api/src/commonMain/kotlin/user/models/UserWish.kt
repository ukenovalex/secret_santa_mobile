package user.models

import kotlinx.serialization.Serializable

@Serializable
data class UserWish(
    val id: Int,
    val message: String,
)
