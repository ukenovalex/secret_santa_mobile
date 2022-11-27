package kvault

import com.liftric.kvault.KVault
import configuration.PlatformConfiguration

internal expect class KVaultStore constructor(configuration: PlatformConfiguration) {
    val store: KVault
}