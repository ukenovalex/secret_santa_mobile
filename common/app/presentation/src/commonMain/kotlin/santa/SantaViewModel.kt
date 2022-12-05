package santa

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import santa.models.SantaStatus
import user.UserRepository

class SantaViewModel : BaseSharedViewModel<SantaState, Nothing, SantaEvent>(
    initialState = SantaState(
        userName = "",
        giftedName = null,
        isSanta = false,
        fetchStatus = SantaStatus.EMPTY
    )
) {

    private val userRepository: UserRepository = Inject.instance()
    private val santaRepository: SantaRepository = Inject.instance()

    override fun obtainEvent(viewEvent: SantaEvent) {
        when (viewEvent) {
            is SantaEvent.FetchSantaInfo -> fetchSantaInfo()
            is SantaEvent.BecomeSanta -> becomeSanta()
            is SantaEvent.ChangeFetchStatus -> changeFetchStatus(viewEvent.status)
        }
    }

    private fun changeFetchStatus(status: SantaStatus) {
        viewState = viewState.copy(fetchStatus = status)
    }

    private fun becomeSanta() {
        viewModelScope.launch {
            try {
                val response = santaRepository.becomeSanta()
                viewState = viewState.copy(giftedName = response.name, isSanta = true)
            } catch (e: RuntimeException) {
                viewState = viewState.copy(fetchStatus = SantaStatus.BECOME_ERROR)
                println(e.message)
            }
        }
    }

    private fun fetchSantaInfo() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(fetchStatus = SantaStatus.LOADING)
                val response = userRepository.fetchUserInfo()
                if (response.isSanta) {
                    fetchGiftedUser()
                }
                viewState = viewState.copy(
                    userName = response.name,
                    fetchStatus = SantaStatus.SUCCESS
                )
            } catch (e: RuntimeException) {
                viewState = viewState.copy(fetchStatus = SantaStatus.ERROR)
                println(e.message)
            }
        }
    }

    private suspend fun fetchGiftedUser() {
        val response = santaRepository.fetchGiftedUser()
        viewState = viewState.copy(giftedName = response.name, isSanta = true)
    }
}