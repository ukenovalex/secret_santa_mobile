package user

import user.models.AddWishStatus
import user.models.FetchUserStatus
import user.models.RemoveWishStatus
import user.models.UserWish

data class UserState(
    val id: Int?,
    val email: String?,
    val name: String?,
    val isSanta: Boolean?,
    val fetchUserStatus: FetchUserStatus,
    val addWishStatus: AddWishStatus,
    val removeWishStatus: RemoveWishStatus,
    val wishes: List<UserWish>?,
    val currentWishValue: String,
)