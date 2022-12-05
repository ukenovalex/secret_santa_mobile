package register

import register.model.RegisterStatus

sealed class RegisterEvent {
    data class InputName(val value: String) : RegisterEvent()
    data class InputEmail(val value: String) : RegisterEvent()
    data class InputPassword(val value: String) : RegisterEvent()
    data class ChangeFetchStatus(val status: RegisterStatus) : RegisterEvent()
    object PressRegister : RegisterEvent()
}