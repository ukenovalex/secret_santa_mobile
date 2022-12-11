package user

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import user.models.*

class UserViewModel : BaseSharedViewModel<UserState, Nothing, UserEvent>(
    initialState = UserState(
        id = null,
        email = null,
        name = null,
        isSanta = null,
        wishes = null,
        fetchUserStatus = FetchUserStatus.EMPTY,
        currentWishValue = "",
        addWishStatus = AddWishStatus.EMPTY,
        removeWishStatus = RemoveWishStatus.EMPTY,
        fetchUserListStatus = FetchUserListStatus.EMPTY,
        users = listOf()
    )
) {

    private val repository: UserRepository = Inject.instance()

    override fun obtainEvent(viewEvent: UserEvent) {
        when (viewEvent) {
            is UserEvent.GetUserInfo -> fetchUserInfo()
            is UserEvent.AddWish -> addWish()
            is UserEvent.RemoveWish -> removeWish(viewEvent.id)
            is UserEvent.InputWish -> inputWish(viewEvent.value)
            is UserEvent.ChangeFetchUserStatus -> changeFetchUserStatus(viewEvent.status)
            is UserEvent.ChangeAddWishStatus -> changeAddWishStatus(viewEvent.status)
            is UserEvent.ChangeRemoveWishStatus -> changeRemoveWishStatus(viewEvent.status)
            is UserEvent.GetAllUsers -> getAllUsers()
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(fetchUserListStatus = FetchUserListStatus.LOADING)
                val users = repository.fetchUserList()
                viewState = viewState.copy(
                    fetchUserListStatus = FetchUserListStatus.SUCCESS, users = users
                )
            } catch (e: RuntimeException) {
                viewState = viewState.copy(fetchUserListStatus = FetchUserListStatus.ERROR)
            }
        }
    }

    private fun changeRemoveWishStatus(status: RemoveWishStatus) {
        viewState = viewState.copy(removeWishStatus = status)
    }

    private fun changeAddWishStatus(status: AddWishStatus) {
        viewState = viewState.copy(addWishStatus = status)
    }

    private fun changeFetchUserStatus(status: FetchUserStatus) {
        viewState = viewState.copy(fetchUserStatus = status)
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
                viewState = viewState.copy(addWishStatus = AddWishStatus.LOADING)
                val wish = repository.addWish(AddWishRequest(message = viewState.currentWishValue))
                val newList = viewState.wishes?.toMutableList()
                newList?.add(UserWish(id = wish.id, message = wish.message))
                viewState = viewState.copy(
                    wishes = newList, currentWishValue = "", addWishStatus = AddWishStatus.SUCCESS
                )
            } catch (e: RuntimeException) {
                viewState = viewState.copy(addWishStatus = AddWishStatus.ERROR)
                println(e.message)
            }
        }
    }

    private fun removeWish(id: Int) {
        viewModelScope.launch {
            viewState = viewState.copy(removeWishStatus = RemoveWishStatus.LOADING)
            val wish = viewState.wishes?.find { it.id == id }
            try {
                val newList = viewState.wishes?.filter { it.id != id }
                viewState = viewState.copy(wishes = newList)
                repository.removeWish(RemoveWishRequest(id = id))
                viewState = viewState.copy(removeWishStatus = RemoveWishStatus.SUCCESS)
            } catch (e: RuntimeException) {
                if (wish != null) {
                    val oldList = viewState.wishes?.toMutableList()
                    oldList?.add(wish)
                    viewState = viewState.copy(
                        wishes = oldList, removeWishStatus = RemoveWishStatus.ERROR
                    )
                }
                println(e.message)
            }
        }
    }
}