package user.models

import kotlinx.serialization.Serializable

@Serializable
data class AddWishResponse(
    val id: Int,
    val message: String,
)
