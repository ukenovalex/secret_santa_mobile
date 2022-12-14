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
            Spacer()
            Text("Introduce yourself, naughty kid!")
                .multilineTextAlignment(.center)
                .foregroundColor(.AppWhite)
                .font(.custom("InriaSans-Bold", size: 36))
            Spacer()
            CommonTextField(label: "Email", hint: "example@gmail.com", enabled: true, isSecure: false){newValue in
                eventHandler(.InputEmail(value: newValue))
            }.padding(.bottom, 20)
            CommonTextField(label: "Password", hint: "", enabled: true, isSecure: true) { newValue in
                eventHandler(.InputPassword(value: newValue))
            }
            Spacer()
            CommonButton(action: {eventHandler(.PressLogin())}, disabled: !viewState.validForm) {
            Text("Done")
                .foregroundColor(Color.secondary)
            }
            Spacer()
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
            validForm: false,
            isUserExist: false
        )
        AuthView(viewState: viewState) { event in }
    }
    
    #if DEBUG
        @objc class func injected() {
            let viewState = AuthState(
                    email: "test@test.test",
                    password: "",
                    loginStatus: LoginStatus.success,
                    validForm: false,
                    isUserExist: false
            )
            let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene
            windowScene?.windows.first?.rootViewController =
                    UIHostingController(rootView: AuthView(viewState: viewState) { event in })
        }
    #endif
}
