package user

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch

class UserViewModel : BaseSharedViewModel<UserState, Nothing, UserEvent>(
    initialState = UserState(
        id = null,
        email = null,
        name = null,
        isSanta = null,
        wishes = null
    )
){

    private val repository: UserRepository = Inject.instance()

    override fun obtainEvent(viewEvent: UserEvent) {
        when(viewEvent) {
            UserEvent.GetUserInfo -> fetchUserInfo()
        }
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            try {
                val response = repository.fetchUserInfo()
                viewState = viewState.copy(
                    id = response.id,
                    email = response.email,
                    name = response.name,
                    isSanta = response.isSanta,
                    wishes = response.wishes
                )
            } catch (e: RuntimeException) {
                println(e.message)
            }
        }
    }
}