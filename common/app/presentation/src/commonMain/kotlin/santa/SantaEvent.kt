package santa

sealed class SantaEvent {
    object FetchSantaInfo: SantaEvent()
    object BecomeSanta: SantaEvent()
}
