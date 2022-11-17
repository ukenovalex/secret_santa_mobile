package auth

sealed class AuthEvent {
    data class InputEmail(val value: String): AuthEvent()
    data class InputPassword(val value: String): AuthEvent()
    object PressLogin : AuthEvent()
}