import SwiftUI
import SharedSDK

struct ContentView: View {

	var body: some View {
        AuthScreen()
	}
}

struct AuthScreen_Previews: PreviewProvider {
    static var previews: some View {
        let viewState = AuthState(
            email: "test@test.test",
            password: "",
            loginStatus: LoginStatus.success,
            validForm: false
        )
        AuthView(viewState: viewState) { event in }
    }
}
