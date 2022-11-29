package register

import org.kodein.di.*
import register.ktor.KtorRegisterRemoteDataSource

val registerModule = DI.Module("registerModule") {
    bind<RegisterRepository>() with singleton { RegisterRepositoryImpl(instance()) }
    bind<KtorRegisterRemoteDataSource>() with provider { KtorRegisterRemoteDataSource(instance()) }
}
