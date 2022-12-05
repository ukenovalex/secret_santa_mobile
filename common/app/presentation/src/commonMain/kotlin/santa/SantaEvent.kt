package santa

import santa.models.SantaStatus

sealed class SantaEvent {
    object FetchSantaInfo: SantaEvent()
    object BecomeSanta: SantaEvent()
    data class ChangeFetchStatus(val status: SantaStatus): SantaEvent()
}
