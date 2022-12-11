package user

import user.models.*

data class UserState(
    val id: Int?,
    val email: String?,
    val name: String?,
    val isSanta: Boolean?,
    val fetchUserStatus: FetchUserStatus,
    val addWishStatus: AddWishStatus,
    val removeWishStatus: RemoveWishStatus,
    val wishes: List<UserWish>?,
    val users: List<FetchUserListItemResponse>,
    val fetchUserListStatus: FetchUserListStatus,
    val currentWishValue: String,
)