package santa

import santa.models.SantaStatus

data class SantaState(
    val userName: String,
    val giftedName: String?,
    val isSanta: Boolean,
    val fetchStatus: SantaStatus
)
