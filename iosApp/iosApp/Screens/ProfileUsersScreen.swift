//
//  ProfileUsersScreen.swift
//  iosApp
//
//  Created by Alex Ukenov on 16.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK



struct ProfileUsersView: View {
    let viewState: UserState
    let eventHandler: (UserEvent) -> Void

    
    var body: some View {
        MainTemplate() {
            Text("Малыши и санты")
                .multilineTextAlignment(.center)
                .foregroundColor(.AppWhite)
                .font(.custom("Pacifico", size: 24))
                .padding(.horizontal, 16)
                .padding(.vertical, 16)
            ScrollView {
                ForEach(0..<viewState.users.count, id: \.self) {index in
                    HStack {
                        Text(viewState.users[index].name)
                            .multilineTextAlignment(.leading)
                            .foregroundColor(.AppWhite)
                            .font(.custom("InriaSans-Regular", size: 16))
                            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                        if (viewState.users[index].isSanta) {
                            Image("santa-logo")
                                .resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(width: 30, height: 30)
                        }
                    }.padding(.horizontal, 8)
                        .padding(.vertical, 8)
                    
                    Divider().overlay(Color.AppWhite)
                }
            }
            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
            .frame(minHeight: 0, maxHeight: .infinity)
        }
        .onAppear() {
            eventHandler(.GetAllUsers())
        }
    }
}


struct ProfileUsersScreen: View {
    let viewModel = UserViewModel()
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            ProfileUsersView(viewState: viewState){ event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}

