package auth

import auth.models.LoginRequest
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch

class AuthViewModel : BaseSharedViewModel<AuthState, AuthAction, AuthEvent>(
    initialState = AuthState(email = "", password = "")
) {
    private val repository: AuthRepository = Inject.instance()

    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewEvent) {
            is AuthEvent.InputEmail -> inputEmail(viewEvent.value)
            is AuthEvent.InputPassword -> inputPassword(viewEvent.value)
            is AuthEvent.PressLogin -> pressLogin()
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
            repository.login(LoginRequest(email = viewState.email, password = viewState.password))
        }
    }
}