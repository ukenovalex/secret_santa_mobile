package ru.flagstudio.secretsanta

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform