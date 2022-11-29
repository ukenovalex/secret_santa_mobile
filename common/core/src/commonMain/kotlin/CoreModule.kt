import kvault.kVaultModule
import org.kodein.di.DI
import utils.utilsModule


val coreModule = DI.Module("coreModule") {
    importAll(
        ktorModule,
        kVaultModule,
        utilsModule,
    )
}
