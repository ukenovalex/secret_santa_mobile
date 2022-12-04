package santa

import org.kodein.di.*
import santa.ktor.KtorSantaRemoteDataSource


val santaModule = DI.Module("santaModule") {
    bind<SantaRepository>() with singleton { SantaRepositoryImpl(instance()) }
    bind<KtorSantaRemoteDataSource>() with provider { KtorSantaRemoteDataSource(instance()) }
}
