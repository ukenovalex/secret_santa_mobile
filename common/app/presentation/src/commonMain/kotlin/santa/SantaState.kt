package santa

import santa.models.SantaDataStatus

data class SantaState(
    val userName: String,
    val giftedName: String?,
    val isSanta: Boolean,
    val fetchDataStatus: SantaDataStatus
)
