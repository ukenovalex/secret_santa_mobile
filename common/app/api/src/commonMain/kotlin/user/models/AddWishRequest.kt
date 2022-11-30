package user.models

import kotlinx.serialization.Serializable

@Serializable
data class AddWishRequest(
    val message: String,
)
