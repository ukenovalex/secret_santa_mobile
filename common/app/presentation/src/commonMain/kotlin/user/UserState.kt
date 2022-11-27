package user

import user.models.UserWish

data class UserState(
    val id: Int?,
    val email: String?,
    val name: String?,
    val isSanta: Boolean?,
    val wishes: List<UserWish>?
)