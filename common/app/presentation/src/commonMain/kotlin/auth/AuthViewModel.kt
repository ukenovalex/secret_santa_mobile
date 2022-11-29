package auth

import auth.model.LoginStatus
import auth.models.LoginRequest
import auth.models.LoginResponse
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import utils.Utils

val initialState = AuthState(
    email = "",
    password = "",
    loginStatus = LoginStatus.EMPTY,
    isLoginExist = false,
    validForm = false
)

class AuthViewModel : BaseSharedViewModel<AuthState, Nothing, AuthEvent>(
    initialState = initialState
) {
    private val repository: AuthRepository = Inject.instance()
    private val utils: Utils = Inject.instance()

    init {
        viewModelScope.launch {
            val response = repository.validate()
            viewState = viewState.copy(isLoginExist = response)
        }
    }

    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewEvent) {
            is AuthEvent.InputEmail -> inputEmail(viewEvent.value)
            is AuthEvent.InputPassword -> inputPassword(viewEvent.value)
            is AuthEvent.PressLogin -> pressLogin()
            is AuthEvent.ChangeLoginStatus -> changeLoginStatus(viewEvent.value)
            is AuthEvent.Logout -> logout()
        }
    }

    private fun inputEmail(value: String) {
        viewState = viewState.copy(email = value)
        validate()
    }

    private fun inputPassword(value: String) {
        viewState = viewState.copy(password = value)
        validate()
    }

    private fun pressLogin() {
        viewModelScope.launch {
            try {
                changeLoginStatus(LoginStatus.LOADING)
                val response: LoginResponse = repository.login(
                    LoginRequest(
                        email = viewState.email,
                        password = viewState.password
                    )
                )
                if (response.jwt.isNotBlank()) {
                    repository.saveToken(response.jwt)
                    changeLoginStatus(LoginStatus.SUCCESS)
                    viewState = viewState.copy(isLoginExist = true)
                }
            } catch (e: RuntimeException) {
                changeLoginStatus(LoginStatus.ERROR)
                println(e.message)
            }
        }
    }

    private fun changeLoginStatus(loginStatus: LoginStatus) {
        viewState = viewState.copy(loginStatus = loginStatus)
    }

    private fun logout() {
        viewState = initialState
        repository.logout()
    }

    private fun validate() {
        viewState = viewState.copy(
            validForm = utils.validateEmail(viewState.email) && utils.validatePassword(viewState.password)
        )
    }

    override fun onCleared() {
        super.onCleared()
        viewState = initialState
    }
}