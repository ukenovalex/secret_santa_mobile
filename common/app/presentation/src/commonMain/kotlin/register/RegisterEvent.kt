package register

sealed class RegisterEvent {
    data class InputName(val value: String) : RegisterEvent()
    data class InputEmail(val value: String) : RegisterEvent()
    data class InputPassword(val value: String) : RegisterEvent()
    object PressRegister : RegisterEvent()
}