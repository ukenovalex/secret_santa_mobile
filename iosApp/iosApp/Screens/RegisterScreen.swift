//
//  RegistrationScreen.swift
//  iosApp
//
//  Created by Yegor Anisimov on 07.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct RegisterView: View {
    let viewState: RegisterState
    let eventHandler: (RegisterEvent) -> Void
    private let viewModel = UserViewModel()
        
    var body: some View {
        MainTemplate() {
            Spacer()
            Text("Introduce yourself, naughty kid!")
                .multilineTextAlignment(.center)
                .foregroundColor(.AppWhite)
                .font(.custom("InriaSans-Bold", size: 32))
            Spacer()
            CommonTextField(label: "Name", hint: "Slacha", enabled: true, isSecure: false) { _ in
                print("any")
            }.padding(.bottom, 20)
            CommonTextField(label: "Email", hint: "example@gmail.com", enabled: true, isSecure: false) { _ in
                print("213")
            }.padding(.bottom, 20)
            CommonTextField(label: "Password", hint: "", enabled: true, isSecure: true) { _ in
                print("213")
            }
            Spacer()
            CommonButton(action: {print("123")}) {
            Text("Done")
                .foregroundColor(Color.secondary)
            }
            Spacer()
        }
    }
}

struct RegisterScreen: View {
    private let viewModel = RegisterViewModel()
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            RegisterView(viewState: viewState) { event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}

class RegisterScreen_Previews: PreviewProvider {
    static var previews: some View {
        let viewState = RegisterState(
            name: "Yegor",
            email: "test@test.test",
            password: "",
            validForm: false,
            status: RegisterStatus.success
        )
        RegisterView(viewState: viewState) { event in }
    }
    
    #if DEBUG
        @objc class func injected() {
            let viewState = RegisterState(
                    name: "Yegor",
                    email: "test@test.test",
                    password: "",
                    validForm: false,
                    status: RegisterStatus.success
            )
            let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene
            windowScene?.windows.first?.rootViewController =
                    UIHostingController(rootView: RegisterView(viewState: viewState) { event in })
        }
    #endif
}
