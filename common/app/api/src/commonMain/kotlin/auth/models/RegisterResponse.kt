package auth.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val email: String,
    val name: String,
)
