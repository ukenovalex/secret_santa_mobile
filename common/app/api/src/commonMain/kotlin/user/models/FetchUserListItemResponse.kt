package user.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchUserListItemResponse(
    val name: String,
    val isSanta: Boolean
)
