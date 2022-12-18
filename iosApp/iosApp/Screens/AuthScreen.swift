//
//  AuthScreen.swift
//  iosApp
//
//  Created by Slava Rykov on 03.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK
import UIPilot

struct AuthView: View {
    let viewState: AuthState
    let eventHandler: (AuthEvent) -> Void
    private let viewModel = UserViewModel()
    
    @EnvironmentObject var pilot: UIPilot<AppRoute>
        
    var body: some View {
        MainTemplate() {
            Spacer()
            Text("Добро пожаловать, санта-малютка!")
                .multilineTextAlignment(.center)
                .foregroundColor(.AppWhite)
                .font(.custom("InriaSans-Bold", size: 36))
            Spacer()
            CommonTextField(label: "Email",
                            hint: "example@flagstudio.ru",
                            enabled: true,
                            isSecure: false) { newValue in
                    eventHandler(.InputEmail(value: newValue))
                }
            .padding(.bottom, 20)
            CommonTextField(label: "Пароль:",
                            hint: "Твой секретик...",
                            enabled: true,
                            isSecure: true) { newValue in
                eventHandler(.InputPassword(value: newValue))
            }
            Spacer()
            CommonButton(label: "Войти",
                         disabled: !viewState.validForm,
                         action: {eventHandler(.PressLogin())})
            Spacer()
        }
        .onChange(of: viewState) { newState in
            if (newState.loginStatus == LoginStatus.success && newState.isUserExist) {
                pilot.push(.TabNavigator)
            }
            if (newState.loginStatus == LoginStatus.success && !newState.isUserExist) {
                pilot.push(.AuthInterests)
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
            validForm: false,
            isUserExist: false
        )
        AuthView(viewState: viewState) { event in }
    }
}
