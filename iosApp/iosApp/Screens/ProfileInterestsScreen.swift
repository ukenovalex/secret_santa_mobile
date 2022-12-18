//
//  ProfileInterestsScreen.swift
//  iosApp
//
//  Created by Alex Ukenov on 16.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK


struct ProfileInterestsView: View {
    let viewState: UserState
    let eventHandler: (UserEvent) -> Void
    
    var body: some View {
        InterestComponent(viewState: viewState, eventHandler: eventHandler)
    }
}


struct ProfileInterestsScreen: View {
    let viewModel = UserViewModel()
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            ProfileInterestsView(viewState: viewState){ event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}
