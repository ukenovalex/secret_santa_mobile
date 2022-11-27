package auth.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val jwt: String
)
