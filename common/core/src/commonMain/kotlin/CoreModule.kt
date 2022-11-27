import kvault.kVaultModule
import org.kodein.di.DI


val coreModule = DI.Module("coreModule") {
    importAll(
        ktorModule,
        kVaultModule
    )
}
