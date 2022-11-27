package auth

import auth.ktor.KtorAuthRemoteDataSource
import auth.kvalut.KVaultAuthRemoteDataSource
import org.kodein.di.*

val authModule = DI.Module("authModule") {
    bind<AuthRepository>() with singleton {
        AuthRepositoryImpl(instance(), instance())
    }
    bind<KtorAuthRemoteDataSource>() with provider {
        KtorAuthRemoteDataSource(instance())
    }
    bind<KVaultAuthRemoteDataSource>() with provider {
        KVaultAuthRemoteDataSource(instance())
    }
}
