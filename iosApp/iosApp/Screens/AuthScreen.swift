//
//  AuthScreen.swift
//  iosApp
//
//  Created by Slava Rykov on 03.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct AuthView: View {
    let viewState: AuthState
    let eventHandler: (AuthEvent) -> Void
    private let viewModel = UserViewModel()
        
    var body: some View {
        MainTemplate() {
            CommonTextField(label: "Egor yeto anal!", hint: "anal", enabled: true, isSecure: false) { _ in
                print("any")
            }
            CommonButton(action: {}) {
                Text("New World")
                    .foregroundColor(Color.black)
            }
        }
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

class AuthScreen_Previews: PreviewProvider {
    static var previews: some View {
        let viewState = AuthState(
            email: "test@test.test",
            password: "",
            loginStatus: LoginStatus.success,
            validForm: false
        )
        AuthView(viewState: viewState) { event in }
    }
    
    #if DEBUG
        @objc class func injected() {
            let viewState = AuthState(
                    email: "test@test.test",
                    password: "",
                    loginStatus: LoginStatus.success,
                    validForm: false
            )
            let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene
            windowScene?.windows.first?.rootViewController =
                    UIHostingController(rootView: AuthView(viewState: viewState) { event in })
        }
    #endif
}

