package kvault

import com.liftric.kvault.KVault
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val kVaultModule = DI.Module("kVaultModule") {
    bind<KVault>() with singleton {
        KVaultStore(instance()).store
    }
}

