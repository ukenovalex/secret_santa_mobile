package register.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val isExist: Boolean
)
