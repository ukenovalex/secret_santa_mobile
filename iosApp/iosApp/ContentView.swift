import SwiftUI
import SharedSDK

struct ContentView: View {

	var body: some View {
        AuthScreen()
	}
}

struct AuthScreen: View {
    private let viewModel = AuthViewModel()
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            AuthView(viewState: viewState) { event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}

struct UserView: View {
    let viewState: UserState
    let eventHandler: (UserEvent) -> Void
    
    var body: some View {
        if (viewState.name != nil) {
            Text(viewState.name!)
        }
        Button(action: {eventHandler(.GetUserInfo())}, label: {Text("Get User Info")})
    }
    
}

struct AuthView: View {
    let viewState: AuthState
    let eventHandler: (AuthEvent) -> Void
    private let viewModel = UserViewModel()
        
    var body: some View {
        VStack {
            if (!viewState.isLoginExist) {
                Text("Authorization")
                CommonTextField(label: "Name", hint: "Email", enabled: true) { newValue in
                    if (viewState.loginStatus != LoginStatus.empty) {
                        eventHandler(.ChangeLoginStatus(value: LoginStatus.empty))
                    }
                    eventHandler(.InputEmail(value: newValue))
                }
                CommonTextField(label: "Password", hint: "", enabled: true, isSecure: true) { newValue in
                    if (viewState.loginStatus != LoginStatus.empty) {
                        eventHandler(.ChangeLoginStatus(value: LoginStatus.empty))
                    }
                    eventHandler(.InputPassword(value: newValue))
                }
                Button(action: {eventHandler(.PressLogin())}, label: {Text("Login")})
                if (viewState.loginStatus == LoginStatus.loading) {
                    Text("Загрузка")
                }
                if (viewState.loginStatus == LoginStatus.error) {
                    Text("Ошибка")
                }
                if (viewState.loginStatus == LoginStatus.success) {
                    Text("Успех!")
                }
            } else {
                ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
                    UserView(viewState: viewState) { event in
                        viewModel.obtainEvent(viewEvent: event)
                    }
                }
                Button(action: {eventHandler(.Logout())}, label: {Text("Log-Out")})
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
