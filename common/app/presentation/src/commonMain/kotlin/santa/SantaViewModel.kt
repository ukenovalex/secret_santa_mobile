package santa

import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import santa.models.SantaDataStatus
import user.UserRepository

class SantaViewModel : BaseSharedViewModel<SantaState, Nothing, SantaEvent>(
    initialState = SantaState(
        userName = "",
        doneeName = null,
        isSanta = false,
        fetchDataStatus = SantaDataStatus.EMPTY
    )
) {

    private val userRepository: UserRepository = Inject.instance()

    override fun obtainEvent(viewEvent: SantaEvent) {
        when(viewEvent) {
            is SantaEvent.FetchSantaInfo -> fetchSantaInfo()
            is SantaEvent.BecomeSanta -> becomeSanta()
        }
    }

    private fun becomeSanta() {

    }

    private fun fetchSantaInfo() {
        viewModelScope.launch {
            try {
                viewState = viewState.copy(fetchDataStatus = SantaDataStatus.LOADING)
                val response = userRepository.fetchUserInfo()
                if (response.isSanta) {
                    // TODO Request donee
                } else {
                    viewState = viewState.copy(userName = response.name, fetchDataStatus = SantaDataStatus.SUCCESS)
                }
            } catch (e: RuntimeException) {
                viewState = viewState.copy(fetchDataStatus = SantaDataStatus.ERROR)
                println(e.message)
            }
        }
    }
}