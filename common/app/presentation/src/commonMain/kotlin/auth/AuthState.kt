package auth

import auth.model.LoginStatus

data class AuthState(
    val email: String,
    val password: String,
    val loginStatus: LoginStatus,
    val isLoginExist: Boolean
)