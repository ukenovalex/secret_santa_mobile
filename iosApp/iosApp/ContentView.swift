import SwiftUI
import SharedSDK
import UIPilot

struct ContentView: View {
    init(){
            UINavigationBar.setAnimationsEnabled(false)
        UINavigationBar.
    }
    @StateObject var pilot = UIPilot(initial: AppRoute.AuthInterests)
    
	var body: some View {
            UIPilotHost(pilot) { route in
                switch route {
                case .Welcome: WelcomeScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .Auth: AuthScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .AuthInterests: InterestsScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .Profile: UserProfileScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .ProfileInterests: ProfileInterestsScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                case .ProfileUsers: ProfileUsersScreen().navigationBarBackButtonHidden(true).uipNavigationBarHidden(true)
                }
                switch route {
                case .ProfileUsers:
                    BottomNavBar(route: route)
                case .ProfileInterests:
                    BottomNavBar(route: route)
                case .Profile:
                    BottomNavBar(route: route)
                case .Welcome:
                    EmptyView()
                case .Auth:
                    EmptyView()
                case .AuthInterests:
                    EmptyView()
                }
            }
            .ignoresSafeArea()
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .padding(.top, -16)
       
	}
}
