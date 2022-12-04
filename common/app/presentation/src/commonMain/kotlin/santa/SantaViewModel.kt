package santa

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import santa.models.SantaDataStatus
import user.UserRepository

class SantaViewModel : BaseSharedViewModel<SantaState, Nothing, SantaEvent>(
    initialState = SantaState(
        userName = "",
        giftedName = null,
        isSanta = false,
        fetchDataStatus = SantaDataStatus.EMPTY
    )
) {

    private val userRepository: UserRepository = Inject.instance()
    private val santaRepository: SantaRepository = Inject.instance()

    override fun obtainEvent(viewEvent: SantaEvent) {
        when(viewEvent) {
            is SantaEvent.FetchSantaInfo -> fetchSantaInfo()
            is SantaEvent.BecomeSanta -> becomeSanta()
        }
    }

    private fun becomeSanta() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(fetchDataStatus = SantaDataStatus.LOADING)
                val response = santaRepository.becomeSanta()
                viewState = viewState.copy(giftedName = response.name)
            } catch (e: RuntimeException) {
                viewState = viewState.copy(fetchDataStatus = SantaDataStatus.ERROR)
                println(e.message)
            }
        }
    }

    private fun fetchSantaInfo() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(fetchDataStatus = SantaDataStatus.LOADING)
                val response = userRepository.fetchUserInfo()
                if (response.isSanta) {
                    fetchGiftedUser()
                }
                viewState = viewState.copy(userName = response.name, fetchDataStatus = SantaDataStatus.SUCCESS)
            } catch (e: RuntimeException) {
                viewState = viewState.copy(fetchDataStatus = SantaDataStatus.ERROR)
                println(e.message)
            }
        }
    }

    private suspend fun fetchGiftedUser() {
        val response = santaRepository.fetchGiftedUser()
        viewState = viewState.copy(giftedName = response.name, isSanta = true)
    }
}