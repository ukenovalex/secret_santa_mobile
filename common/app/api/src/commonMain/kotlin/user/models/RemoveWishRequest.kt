package user.models

import kotlinx.serialization.Serializable

@Serializable
data class RemoveWishRequest(
    val id: Int,
)
