package register

import auth.AuthRepository
import auth.models.LoginRequest
import auth.models.LoginResponse
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

    private val registerRepository: RegisterRepository = Inject.instance()
    private val authRepository: AuthRepository = Inject.instance()
    private val utils: Utils = Inject.instance()

    override fun obtainEvent(viewEvent: RegisterEvent) {
        when (viewEvent) {
            is RegisterEvent.InputEmail -> inputEmail(viewEvent.value)
            is RegisterEvent.InputName -> inputName(viewEvent.value)
            is RegisterEvent.InputPassword -> inputPassword(viewEvent.value)
            is RegisterEvent.PressRegister -> onPressRegister()
            is RegisterEvent.ChangeFetchStatus -> changeFetchStatus(viewEvent.status)
        }
    }

    private fun changeFetchStatus(status: RegisterStatus) {
        viewState = viewState.copy(status = status)
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
                registerRepository.fetchRegister(
                    RegisterRequest(
                        name = viewState.name,
                        email = viewState.email,
                        password = viewState.password
                    )
                )
                fetchLogin(email = viewState.email, password = viewState.password)
                viewState = viewState.copy(status = RegisterStatus.SUCCESS)
            } catch (e: RuntimeException) {
                viewState = viewState.copy(status = RegisterStatus.ERROR)
                println(e.message)
            }
        }
    }

    private suspend fun fetchLogin(email: String, password: String) {
        val response: LoginResponse = authRepository.login(LoginRequest(
            email = email,
            password = password
        ))
        if (response.jwt.isNotBlank()) {
            authRepository.saveToken(response.jwt)
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

    override fun onCleared() {
        viewState = initialState
    }
}