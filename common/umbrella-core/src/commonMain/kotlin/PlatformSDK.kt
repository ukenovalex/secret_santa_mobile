import auth.authModule
import configuration.PlatformConfiguration
import di.Inject
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton
import user.userModule

object PlatformSDK {
    fun init(
        configuration: PlatformConfiguration
    ) {
        val umbrellaModule = DI.Module(
            name = "umbrella",
            init = {
                bind<PlatformConfiguration>() with singleton { configuration }
            }
        )

        Inject.createDependency(
            DI {
                importAll(
                    umbrellaModule,
                    coreModule,
                    authModule,
                    userModule
                )
            }.direct
        )
    }
}