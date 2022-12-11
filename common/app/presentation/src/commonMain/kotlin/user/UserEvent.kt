package user

import user.models.AddWishStatus
import user.models.FetchUserStatus
import user.models.RemoveWishStatus

sealed class UserEvent {
    object GetUserInfo : UserEvent()
    object GetAllUsers : UserEvent()
    object AddWish : UserEvent()
    data class RemoveWish(val id: Int) : UserEvent()
    data class InputWish(val value: String) : UserEvent()
    data class ChangeFetchUserStatus(val status: FetchUserStatus) : UserEvent()
    data class ChangeAddWishStatus(val status: AddWishStatus) : UserEvent()
    data class ChangeRemoveWishStatus(val status: RemoveWishStatus) : UserEvent()
}