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
        MainTemplate() {
            Spacer()
            Text("Скажи, что ты хочешь от санты?")
                .multilineTextAlignment(.center)
                .foregroundColor(.AppWhite)
                .font(.custom("InriaSans-Bold", size: 36))
                .padding(.horizontal, 16)
            Spacer()
            BindingTextField(
                currentValue: viewState.currentWishValue,
                label: "Хочу",
                            hint: "Название подарка",
                            enabled: true,
                            isSecure: false) { newValue in
                eventHandler(.InputWish(value: newValue))
                }
            .padding(.bottom, 20)
            Spacer()
            CommonButton(label: "Добавить",
                         disabled: viewState.currentWishValue.count == 0,
                         action: {eventHandler(.AddWish())})
            Spacer()
            HStack {
                if (viewState.wishes != nil) {
                    ForEach(0..<viewState.wishes!.count,id: \.self) { index in
                        Text(viewState.wishes![index].message).onTapGesture {
                            eventHandler(.RemoveWish(id: viewState.wishes![index].id))
                        }
                        .foregroundColor(.AppRed)
                        .font(.custom("Roboto-Medium", size: 16))
                        .padding(.vertical, 8)
                        .padding(.horizontal, 13)
                        .background(Color.AppWhite.cornerRadius(20))
                        
                    }
                }
            }
            Spacer()
            VStack {
                CommonButton(label: "Хочу",
                             disabled: false,
                             action: {
                    pilot.push(.TabNavigator)
                    
                }
                )
                Spacer().frame(height: 40.0)
            }
            
        }
        .onAppear() {
            eventHandler(.GetUserInfo())
        }
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
