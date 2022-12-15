//
//  InterestsScreen.swift
//  iosApp
//
//  Created by Alex Ukenov on 15.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK


struct InterestsView: View {
    let viewState: UserState
    let eventHandler: (UserEvent) -> Void
    
    var body: some View {
        MainTemplate() {
            Spacer()
            Text("Скажи, что ты хочешь от санты?")
                .multilineTextAlignment(.center)
                .foregroundColor(.AppWhite)
                .font(.custom("InriaSans-Bold", size: 36))
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
                         disabled: false,
                         action: {eventHandler(.AddWish())})
            Spacer()
            HStack {
                if (viewState.wishes != nil) {
                    ForEach(0..<viewState.wishes!.count,id: \.self) { index in
                        Text(viewState.wishes![index].message).onTapGesture {
                            eventHandler(.RemoveWish(id: viewState.wishes![index].id))
                        }
                    }
                }
            }
            Spacer()
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
