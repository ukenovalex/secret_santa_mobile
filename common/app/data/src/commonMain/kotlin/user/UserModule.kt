package user

import org.kodein.di.*
import user.ktor.KtorUserRemoteDataSource


val userModule = DI.Module("userModule") {
    bind<UserRepository>() with singleton {
        UserRepositoryImpl(instance())
    }
    bind<KtorUserRemoteDataSource>() with provider {
        KtorUserRemoteDataSource(instance())
    }
}
