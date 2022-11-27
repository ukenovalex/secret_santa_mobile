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

struct AuthView: View {
    let viewState: AuthState
    let eventHandler: (AuthEvent) -> Void
        
    var body: some View {
        VStack {
            Text("Authorization")
            CommonTextField(hint: "Email", enabled: true) { newValue in
                if (viewState.loginStatus != LoginStatus.empty) {
                    eventHandler(.ChangeLoginStatus(value: LoginStatus.empty))
                }
                eventHandler(.InputEmail(value: newValue))
            }
            CommonTextField(hint: "Password", enabled: true, isSecure: true) { newValue in
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
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
