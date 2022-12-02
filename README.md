# Как запустить проект.

Устанавливаем JDK
  - ``` brew tap homebrew/cask-versions ```
  - ``` brew install --cask zulu11 ```

Устанавливаем Android Studio
  - https://developer.android.com/studio
  
Устанавливаем CocoaPods
  - ``` brew install cocoapods ```
  
В корне проекта запускаем
  - Windows - ``` gradlew build ```
  - Unix - ``` ./gradlew build ```
  
Либо вариант побыстрее 
  - Запускаем проект в Android Studio и жмем на ```Gradle Sync``` (это слоник со стрелочкой в правом верхнем углу)

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
    val validForm: Boolean // true - если введенный логин и пароль валидны
    ```
  - AuthEvent
    ```
    data class InputEmail(val value: String): AuthEvent() // Событие ввода емайла
    data class InputPassword(val value: String): AuthEvent() // Событие ввода пароля
    data class ChangeLoginStatus(val value: LoginStatus): AuthEvent() // Принудительно изменить статус логина
    object PressLogin : AuthEvent() // Нажать на кнопку залогиниться (email и password полетят на сервер)
    object Logout : AuthEvent() // Событие выхода из аккаунта
    object CheckLoginStatus : AuthEvent() // Событие проверки залогиннености (чекает ранее сохраненный jwt токен)
    ```
  - LoginStatus
    ```
    enum class LoginStatus {
      EMPTY, // Этот статус может быть только на Welcome экране. Для его изменения первым делом выполняем AuthEvent.CheckLoginStatus 
      NOT_VERIFIED, // Этот статус может быть только на странице авторизации или регистрации. Тоесть когда у нас нет токена или он просрочен
      LOADING, // Это статус когда мы логируемся или проверяем авторизованны ли мы
      SUCCESS, // Этот статус когда мы успешно прошли верификацию и можем пользоваться другими страницами (страница логина и регистрации не доступны)
      ERROR // Когда произошла какая то ошибка в процессе любой операции
    }
    ```
  
RegisterViewModel - ViewModel для регистрации. Содержит в себе:
  - RegisterState
    ```
    val name: String, // Имя пользователя
    val email: String, // E-mail пользователя
    val password: String, // Пароль пользователя
    val validForm: Boolean, // true - если введенные имя, логин и пароль валидны
    val status: RegisterStatus // Статус регистрации (см. RegisterStatus)
    ```
  - RegisterEvent
    ```
    data class InputName(val value: String) : RegisterEvent() // Событие ввода имени
    data class InputEmail(val value: String) : RegisterEvent() // Событие ввода емайла
    data class InputPassword(val value: String) : RegisterEvent() // Событие ввода пароля
    object PressRegister : RegisterEvent() // Событие регистрации (всё летит на сервер)
    ```
  - RegisterStatus
    ```
    // Аналогично
    enum class RegisterStatus {
      EMPTY,
      LOADING,
      SUCCESS,
      ERROR
    }
    ```

