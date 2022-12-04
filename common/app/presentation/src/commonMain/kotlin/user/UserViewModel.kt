package user

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import user.models.AddWishRequest
import user.models.FetchUserStatus
import user.models.RemoveWishRequest
import user.models.UserWish

class UserViewModel : BaseSharedViewModel<UserState, Nothing, UserEvent>(
    initialState = UserState(
        id = null,
        email = null,
        name = null,
        isSanta = null,
        wishes = null,
        fetchUserStatus = FetchUserStatus.EMPTY,
        currentWishValue = ""
    )
){

    private val repository: UserRepository = Inject.instance()

    override fun obtainEvent(viewEvent: UserEvent) {
        when(viewEvent) {
            is UserEvent.GetUserInfo -> fetchUserInfo()
            is UserEvent.AddWish -> addWish()
            is UserEvent.RemoveWish -> removeWish(viewEvent.id)
            is UserEvent.InputWish -> inputWish(viewEvent.value)
        }
    }

    private fun inputWish(value: String) {
        viewState = viewState.copy(currentWishValue = value)
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            viewState = viewState.copy(fetchUserStatus = FetchUserStatus.LOADING)
            try {
                val response = repository.fetchUserInfo()
                viewState = viewState.copy(
                    id = response.id,
                    email = response.email,
                    name = response.name,
                    isSanta = response.isSanta,
                    wishes = response.wishes,
                    fetchUserStatus = FetchUserStatus.SUCCESS
                )
            } catch (e: RuntimeException) {
                viewState = viewState.copy(fetchUserStatus = FetchUserStatus.ERROR)
                println(e.message)
            }
        }
    }

    private fun addWish() {
        viewModelScope.launch {
            try {
                val wish = repository.addWish(AddWishRequest(message = viewState.currentWishValue))
                val newList = viewState.wishes?.toMutableList()
                newList?.add(UserWish(id = wish.id, message = wish.message))
                viewState = viewState.copy(wishes = newList, currentWishValue = "")
            } catch (e: RuntimeException) {
                println(e.message)
            }
        }
    }

    private fun removeWish(id: Int) {
        viewModelScope.launch {
            val wish = viewState.wishes?.find { it.id == id }
            try {
                val newList = viewState.wishes?.filter { it.id != id }
                viewState = viewState.copy(wishes = newList)
                repository.removeWish(RemoveWishRequest(id = id))
            } catch (e: RuntimeException) {
                if (wish != null) {
                    val newList = viewState.wishes?.toMutableList()
                    newList?.add(wish)
                    viewState = viewState.copy(wishes = newList)
                }
                println(e.message)
            }
        }
    }
}