package user

sealed class UserEvent {
    object GetUserInfo : UserEvent()
}