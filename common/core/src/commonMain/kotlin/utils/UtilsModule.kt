package utils

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val utilsModule = DI.Module("utilsModule") {
    bind<Utils>() with singleton { Utils }
}
