package auth

import auth.models.LoginRequest
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val authRepository: AuthRepository = Inject.instance()
    private val _actions = Channel<AuthAction>()
    val actions: Flow<AuthAction> get() = _actions.receiveAsFlow()

    fun sendEvent(event: AuthEvent) {}

    fun pressLogin() {
        viewModelScope.launch {
            authRepository.login(LoginRequest(
                email = email.value,
                password = password.value
            ))
            email.value = ""
            password.value = ""
        }
    }
}