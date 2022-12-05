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
            CommonTextField(label: "anal", hint: "anal", enabled: true, isSecure: false) { _ in
                print("any")
            }
            CommonButton(action: {}) {
                Text("New test")
                    .foregroundColor(Color.secondary)
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

