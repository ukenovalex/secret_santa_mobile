# Как запустить проект.

Устанавливаем JDK
  - ``` brew tap homebrew/cask-versions ```
  - ``` brew install --cask zulu11 ```
  
Устанавливаем CocoaPods
  - ``` sudo gem install cocoapods ```

В корне проекта запускаем
  - Windows - ``` gradlew build ```
  - Unix - ``` ./gradlew build ```

Переходим в директорию iosApp
  - ``` cd iosApp ```

Запускаем следующую команду
  - ``` pod install ```

Затем
  - Открываем iosApp в XCode
  - Жмем ``` Product->Analyze ```
  
# API
Вся бизнес логика лежит в пакете SharedSDK. ``` import SharedSDK ```

AuthViewModel - ViewModel для авторизации. Содержит в себе:
  - AuthState
    ```
    val email: String, // E-mail пользователя
    val password: String, // Пароль пользователя
    val loginStatus: LoginStatus, // Статус логина (см. LoginStatus)
    val isLoginExist: Boolean, // true - если в памяти телефона уже лежит валидный jwt и чувак залогинен
    val validForm: Boolean // true - если введенный логин и пароль валидны
    ```
  - AuthEvent
    ```
    data class InputEmail(val value: String): AuthEvent() // Событие ввода емайла
    data class InputPassword(val value: String): AuthEvent() // Событие ввода пароля
    data class ChangeLoginStatus(val value: LoginStatus): AuthEvent() // Принудительно изменить статус логина
    object PressLogin : AuthEvent() // Нажать на кнопку залогиниться (email и password полетят на сервер)
    object Logout : AuthEvent() // Событие выхода из аккаунта
    ```
  - LoginStatus
    ```
    // Ну тут и так все понятно
    enum class LoginStatus {
      EMPTY,
      LOADING,
      SUCCESS,
      ERROR
    }
    ```
  

