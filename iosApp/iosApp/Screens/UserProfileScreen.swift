//
//  UserProfileScreen.swift
//  iosApp
//
//  Created by Slava Rykov on 03.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK

struct UserView: View {
    let viewState: UserState
    let eventHandler: (UserEvent) -> Void
    
    var body: some View {
        MainTemplate() {
            if (viewState.name != nil) {
                Text(viewState.name!)
            }
            Button(action: {eventHandler(.GetUserInfo())}, label: {Text("Get User Info")})
        }
    }
}


struct UserProfileScreen: View {
    let viewModel = UserViewModel()
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            UserView(viewState: viewState) { event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}
