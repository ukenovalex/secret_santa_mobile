package auth

import auth.model.LoginStatus

sealed class AuthEvent {
    data class InputEmail(val value: String): AuthEvent()
    data class InputPassword(val value: String): AuthEvent()
    data class ChangeLoginStatus(val value: LoginStatus): AuthEvent()
    object PressLogin : AuthEvent()
}