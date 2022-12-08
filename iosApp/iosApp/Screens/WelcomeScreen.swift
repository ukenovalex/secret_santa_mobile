//
//  WelcomeScreen.swift
//  iosApp
//
//  Created by Yegor Anisimov on 07.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct WelcomeView: View {
    var body: some View {
        Text("WelcomeView")
    }
}
//
//struct RegisterScreen: View {
//    private let viewModel = RegisterViewModel()
//
//    var body: some View {
//        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
//            RegisterView(viewState: viewState) { event in
//                viewModel.obtainEvent(viewEvent: event)
//            }
//        }
//    }
//}

//class RegisterScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        let viewState = RegisterState(
//            name: "Yegor",
//            email: "test@test.test",
//            password: "",
//            validForm: false,
//            status: RegisterStatus.success
//        )
//        RegisterView(viewState: viewState) { event in }
//    }
//
//    #if DEBUG
//        @objc class func injected() {
//            let viewState = RegisterState(
//                    name: "Yegor",
//                    email: "test@test.test",
//                    password: "",
//                    validForm: false,
//                    status: RegisterStatus.success
//            )
//            let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene
//            windowScene?.windows.first?.rootViewController =
//                    UIHostingController(rootView: RegisterView(viewState: viewState) { event in })
//        }
//    #endif
//}
