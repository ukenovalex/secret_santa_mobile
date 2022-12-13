# Как запустить проект.

Устанавливаем JDK
  - ``` brew tap homebrew/cask-versions ```
  - ``` brew install --cask zulu11 ```

Устанавливаем Android Studio
  - https://developer.android.com/studio
  
Устанавливаем CocoaPods
  - ``` brew install cocoapods ```
  
Устанавливаем InjectionIII для хот релоада в IOS симуляторе
  - https://apps.apple.com/us/app/injectioniii/id1380446739?mt=12
  
Запускаем проект в Android Studio 
  - Жмем на ```Gradle Sync``` (это слоник со стрелочкой в правом верхнем углу)

Переходим в директорию iosApp
  - ``` cd iosApp ```

Запускаем следующую команду
  - ``` pod install ```

Открываем iosApp в XCode
  - Жмем ``` Product->Analyze ```
  - Ждем не обращая внимания на варнинги (Их будет много. Это нормально!)
  
# API
Вся бизнес логика лежит в пакете SharedSDK. ``` import SharedSDK ```

AuthViewModel - ViewModel для авторизации. Содержит в себе:
  - AuthState
    ```
    val email: String, // E-mail пользователя
    val password: String, // Пароль пользователя
    val loginStatus: LoginStatus, // Статус логина (см. LoginStatus)
    val validForm: Boolean // true - если введенный логин и пароль валидны
    val isUserExist: Boolean // если true навигируемся на профайл если false навигируемся на ввод интересов
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
    
    
# Регистрации и ввода имени больше нет!!
  
  
UserViewModel - ViewModel для пользователя. Содержит в себе:
  - 
  ```
  data class UserState(
    val id: Int?,
    val email: String?,
    val name: String?,
    val isSanta: Boolean?,
    val fetchUserStatus: FetchUserStatus,
    val addWishStatus: AddWishStatus,
    val removeWishStatus: RemoveWishStatus,
    val wishes: List<UserWish>?,
    val currentWishValue: String
  )
  ```
  
  -
  ```
  sealed class UserEvent {
    object GetUserInfo : UserEvent()
    object AddWish : UserEvent()
    data class RemoveWish(val id: Int) : UserEvent()
    data class InputWish(val value: String) : UserEvent()
    data class ChangeFetchUserStatus(val status: FetchUserStatus) : UserEvent()
    data class ChangeAddWishStatus(val status: AddWishStatus) : UserEvent()
    data class ChangeRemoveWishStatus(val status: RemoveWishStatus) : UserEvent()
  }
  ```
  -
  ```
  enum class AddWishStatus {
    EMPTY,
    LOADING,
    SUCCESS,
    ERROR
  }
  ```
  -
  ```
  enum class RemoveWishStatus {
    EMPTY,
    LOADING,
    SUCCESS,
    ERROR
  }
  ```
  -
  ```
  enum class FetchUserStatus {
    EMPTY,
    LOADING,
    SUCCESS,
    ERROR
  }
  ```
  
  SantaViewModel - ViewModel для сантаклаувской логики. Содержит в себе:
  -
  ```
  data class SantaState(
    val userName: String,
    val giftedName: String?,
    val isSanta: Boolean,
    val fetchStatus: SantaStatus
  )
  ```
  -
  ```
  sealed class SantaEvent {
    object FetchSantaInfo: SantaEvent()
    object BecomeSanta: SantaEvent()
    data class ChangeFetchStatus(val status: SantaStatus): SantaEvent()
  }
  ```
  -
  ```
  enum class SantaStatus {
    EMPTY,
    LOADING,
    SUCCESS,
    ERROR,
    BECOME_ERROR,
  }
  ```
 
