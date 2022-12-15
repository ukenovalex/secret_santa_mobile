import SwiftUI
import SharedSDK
import UIPilot

struct ContentView: View {
    @StateObject var pilot = UIPilot(initial: AppRoute.Welcome)
    
	var body: some View {
        UIPilotHost(pilot) { route in
            switch route {
            case .Welcome: WelcomeScreen()
            case .Auth: AuthScreen()
            case .AuthInterests: InterestsScreen()
            case .Profile: UserProfileScreen()
            }
        }
	}
}
