package register

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import register.model.RegisterStatus
import register.models.RegisterRequest
import utils.Utils

val initialState = RegisterState(
    name = "",
    password = "",
    email = "",
    validForm = false,
    status = RegisterStatus.EMPTY
)

class RegisterViewModel : BaseSharedViewModel<RegisterState, Nothing, RegisterEvent>(
    initialState = initialState
) {

    private val repository: RegisterRepository = Inject.instance()
    private val utils: Utils = Inject.instance()

    override fun obtainEvent(viewEvent: RegisterEvent) {
        when (viewEvent) {
            is RegisterEvent.InputEmail -> inputEmail(viewEvent.value)
            is RegisterEvent.InputName -> inputName(viewEvent.value)
            is RegisterEvent.InputPassword -> inputPassword(viewEvent.value)
            is RegisterEvent.PressRegister -> onPressRegister()
        }
    }

    private fun inputName(value: String) {
        viewState = viewState.copy(name = value)
        validate()
    }

    private fun inputEmail(value: String) {
        viewState = viewState.copy(email = value)
        validate()
    }

    private fun inputPassword(value: String) {
        viewState = viewState.copy(password = value)
        validate()
    }

    private fun onPressRegister() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(status = RegisterStatus.LOADING)
                repository.fetchRegister(
                    RegisterRequest(
                        name = viewState.name,
                        email = viewState.email,
                        password = viewState.password
                    )
                )
                viewState = viewState.copy(status = RegisterStatus.SUCCESS)
            } catch (e: RuntimeException) {
                viewState = viewState.copy(status = RegisterStatus.ERROR)
                println(e.message)
            }
        }
    }

    private fun validate() {
        viewState = viewState.copy(
            validForm = utils.validateEmail(viewState.email)
                    && utils.validatePassword(viewState.password)
                    && validateName(viewState.name)
        )
    }

    private fun validateName(name: String): Boolean {
        return name.length >= 3
    }
}