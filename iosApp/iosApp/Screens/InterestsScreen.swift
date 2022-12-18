//
//  InterestsScreen.swift
//  iosApp
//
//  Created by Alex Ukenov on 15.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK
import UIPilot


struct InterestsView: View {
    let viewState: UserState
    let eventHandler: (UserEvent) -> Void
    @EnvironmentObject var pilot: UIPilot<AppRoute>
    
    var body: some View {
        VStack {
            InterestComponent(viewState: viewState, eventHandler: eventHandler)
            VStack {
                CommonButton(label: "Хочу",
                                disabled: false,
                                action: {
                    pilot.push(.TabNavigator)
                    
                }
                )
                Spacer().frame(height: 60.0)
            }
            .ignoresSafeArea()
            .frame(maxWidth: .infinity)
            .background(Color.AppRed)
        }
        .ignoresSafeArea()
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.AppRed)
        
    }
}


struct InterestsScreen: View {
    let viewModel = UserViewModel()
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            InterestsView(viewState: viewState){ event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}
