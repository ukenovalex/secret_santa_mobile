//
//  WelcomeScreen.swift
//  iosApp
//
//  Created by Yegor Anisimov on 07.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK
import UIPilot

struct WelcomeView: View {
    let viewState: AuthState
    let eventHandler: (AuthEvent) -> Void
    
    @EnvironmentObject var pilot: UIPilot<AppRoute>
    
    
    var body: some View {
        ZStack {
            Image("welcome-image")
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(minWidth: 0, maxWidth: .infinity)
                .edgesIgnoringSafeArea(.all)
            VStack() {
                Spacer()
                Text("Ho Ho Ho! Merry Christmas!")
                    .font(.custom("Pacifico", size: 36))
                    .multilineTextAlignment(.center)
                    .rotationEffect(Angle(degrees: -5))
                    .padding(.bottom, 50)
                Image(systemName: "hand.tap")
                    .font(.largeTitle)
                Text("Tap Here")
                    .font(.custom("Pacifico", size: 24))
            }
            .foregroundColor(.AppRed)
            .padding(.horizontal, 24)
            .padding(.bottom, 64)
        }
        .onChange(of: viewState.loginStatus) { status in
            if (status == LoginStatus.notVerified) {
                pilot.popTo(.Welcome, inclusive: true)
                pilot.push(.Auth)
            }
            if (status == LoginStatus.success) {
                pilot.popTo(.Welcome, inclusive: true)
                pilot.push(.TabNavigator)
            }
        }
        .onTapGesture() {
            eventHandler(.CheckLoginStatus())
        }
    }
}

struct WelcomeScreen: View {
    let viewModel = AuthViewModel()
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            WelcomeView(viewState: viewState){ event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}

class WelcomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        WelcomeScreen()
    }
}
