package kvault

import com.liftric.kvault.KVault
import configuration.PlatformConfiguration

internal actual class KVaultStore actual constructor(configuration: PlatformConfiguration) {
    actual val store: KVault = KVault()
}