package santa

import santa.models.SantaStatus

data class SantaState(
    val userName: String,
    val giftedName: String?,
    val giftedWishList: List<String>,
    val isSanta: Boolean,
    val isShowGiftedWishDialog: Boolean,
    val fetchStatus: SantaStatus
)
