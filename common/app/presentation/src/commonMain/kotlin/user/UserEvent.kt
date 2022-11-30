package user

sealed class UserEvent {
    object GetUserInfo : UserEvent()
    object AddWish : UserEvent()
    data class RemoveWish(val id: Int) : UserEvent()
    data class InputWish(val value: String) : UserEvent()
}