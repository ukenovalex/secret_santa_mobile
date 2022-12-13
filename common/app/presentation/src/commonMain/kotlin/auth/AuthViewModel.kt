package auth

import auth.model.LoginStatus
import auth.models.LoginRequest
import auth.models.LoginResponse
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import register.RegisterRepository
import register.models.RegisterRequest
import utils.Utils

val initialState = AuthState(
    email = "",
    password = "",
    loginStatus = LoginStatus.EMPTY,
    validForm = false,
    isUserExist = false,
)

class AuthViewModel : BaseSharedViewModel<AuthState, Nothing, AuthEvent>(
    initialState = initialState
) {
    private val repository: AuthRepository = Inject.instance()
    private val registerRepository: RegisterRepository = Inject.instance()
    private val utils: Utils = Inject.instance()


    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewEvent) {
            is AuthEvent.InputEmail -> inputEmail(viewEvent.value)
            is AuthEvent.InputPassword -> inputPassword(viewEvent.value)
            is AuthEvent.PressLogin -> pressLogin()
            is AuthEvent.ChangeLoginStatus -> changeLoginStatus(viewEvent.value)
            is AuthEvent.Logout -> logout()
            is AuthEvent.CheckLoginStatus -> checkLoginStatus()
        }
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(loginStatus = LoginStatus.LOADING)
                val response = repository.validate()
                viewState = if (response) {
                    viewState.copy(loginStatus = LoginStatus.SUCCESS)
                } else {
                    viewState.copy(loginStatus = LoginStatus.NOT_VERIFIED)
                }
            } catch (e: RuntimeException) {
                viewState = viewState.copy(loginStatus = LoginStatus.ERROR)
            }
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
                viewState = viewState.copy(loginStatus = LoginStatus.LOADING)
                val response = registerRepository.fetchRegister(
                    RegisterRequest(
                        email = viewState.email,
                        password = viewState.password
                    )
                )
                fetchLogin(email = viewState.email, password = viewState.password)
                viewState = viewState.copy(loginStatus = LoginStatus.SUCCESS, isUserExist = response.isExist)
            } catch (e: RuntimeException) {
                viewState = viewState.copy(loginStatus = LoginStatus.ERROR)
                println(e.message)
            }
        }
    }
    private suspend fun fetchLogin(email: String, password: String) {
        try {
            val response: LoginResponse = repository.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
            if (response.jwt.isNotBlank()) {
                repository.saveToken(response.jwt)
            }
        } catch (e: RuntimeException) {
            viewState = viewState.copy(loginStatus = LoginStatus.ERROR)
            println(e.message)
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