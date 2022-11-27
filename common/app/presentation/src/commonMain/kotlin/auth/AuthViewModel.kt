package auth

import auth.model.LoginStatus
import auth.models.LoginRequest
import auth.models.LoginResponse
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch

class AuthViewModel : BaseSharedViewModel<AuthState, AuthAction, AuthEvent>(
    initialState = AuthState(email = "", password = "", loginStatus = LoginStatus.EMPTY)
) {
    private val repository: AuthRepository = Inject.instance()

    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewEvent) {
            is AuthEvent.InputEmail -> inputEmail(viewEvent.value)
            is AuthEvent.InputPassword -> inputPassword(viewEvent.value)
            is AuthEvent.PressLogin -> pressLogin()
            is AuthEvent.ChangeLoginStatus -> changeLoginStatus(viewEvent.value)
        }
    }

    private fun inputEmail(value: String) {
        viewState = viewState.copy(email = value)
    }

    private fun inputPassword(value: String) {
        viewState = viewState.copy(password = value)
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
}