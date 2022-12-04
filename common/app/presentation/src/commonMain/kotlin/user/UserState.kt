package user

import user.models.FetchUserStatus
import user.models.UserWish

data class UserState(
    val id: Int?,
    val email: String?,
    val name: String?,
    val isSanta: Boolean?,
    val fetchUserStatus: FetchUserStatus,
    val wishes: List<UserWish>?,
    val currentWishValue: String,
)