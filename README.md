# Как запустить проект.

Устанавливаем JDK
  - ``` brew tap homebrew/cask-versions ```
  - ``` brew install --cask zulu11 ```
  
Устанавливаем CocoaPods
  - ``` sudo gem install cocoapods ```

В корне проекта запускаем
  - Windows - ``` gradlew build ```
  - Unix - ``` ./gradlew build ```

Переходим в директорию iosApp и запускаем следующую команду
  - ``` pod install ```

Затем
  - Открываем iosApp в XCode
  - Жмем ``` Product->Analyze ```
  
# API
Вся бизнес логика лежит в пакете SharedSDK. ``` import SharedSDK ```

AuthViewModel - ViewModel для авторизации. Содержит в себе:
  - AuthState
    ```
    val email: String,
    val password: String,
    val loginStatus: LoginStatus,
    val isLoginExist: Boolean,
    val validForm: Boolean
    ```
  - AuthEvent
    ```
    data class InputEmail(val value: String): AuthEvent()
    data class InputPassword(val value: String): AuthEvent()
    data class ChangeLoginStatus(val value: LoginStatus): AuthEvent()
    object PressLogin : AuthEvent()
    object Logout : AuthEvent()
    ```
  

