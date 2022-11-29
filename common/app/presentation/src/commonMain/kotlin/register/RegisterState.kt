package register

import register.model.RegisterStatus

data class RegisterState(
    val name: String,
    val email: String,
    val password: String,
    val validForm: Boolean,
    val status: RegisterStatus
)