import SwiftUI
import SharedSDK
import UIPilot

struct ContentView: View {
    @StateObject var pilot = UIPilot(initial: AppRoute.Welcome)
    
	var body: some View {
            UIPilotHost(pilot) { route in
                switch route {
                case .Welcome: WelcomeScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .Auth: AuthScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .AuthInterests: InterestsScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .Profile: UserProfileScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                }
            }
            .ignoresSafeArea()
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .padding(.top, -16)
       
	}
}
