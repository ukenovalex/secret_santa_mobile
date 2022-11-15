package ru.flagstudio.secretsanta.android

import PlatformSDK
import android.app.Application
import configuration.PlatformConfiguration

class SecretSantaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initPlatformSDK()
    }
}

fun SecretSantaApp.initPlatformSDK() =
    PlatformSDK.init(configuration = PlatformConfiguration(
        androidContext = applicationContext
    ))