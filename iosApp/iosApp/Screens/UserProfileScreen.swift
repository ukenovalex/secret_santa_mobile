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
    let viewState: SantaState
    let eventHandler: (SantaEvent) -> Void
    
    var body: some View {
        MainTemplate() {
            VStack {
                if (viewState.fetchStatus == SantaStatus.loading) {
                    Spacer()
                    Text("Загрузка")
                        .multilineTextAlignment(.center)
                        .foregroundColor(.AppWhite)
                        .font(.custom("Pacifico", size: 32))
                        .padding(.horizontal, 16)
                    Spacer()
                }
                if (viewState.fetchStatus == SantaStatus.success) {
                    Spacer().frame(height: 54)
                    Text(viewState.userName)
                        .multilineTextAlignment(.center)
                        .foregroundColor(.AppWhite)
                        .font(.custom("InriaSans-Bold", size: 36))
                        .padding(.horizontal, 16)
                    Spacer().frame(height: 54)
                    if (!viewState.isSanta) {
                        CommonButton(label: "Стать Сантой", disabled: false) {
                            eventHandler(.BecomeSanta())
                        }
                    } else {
                        Text("Твой послушный малыш:")
                            .multilineTextAlignment(.center)
                            .foregroundColor(.AppWhite)
                            .font(.custom("Pacifico", size: 24))
                            .padding(.horizontal, 16)
                            .padding(.vertical, 16)
                        Text(viewState.giftedName ?? "Неизвестно")
                            .multilineTextAlignment(.center)
                            .foregroundColor(.AppWhite)
                            .font(.custom("Pacifico", size: 36))
                            .padding(.horizontal, 16)
                            .padding(.vertical, 16)
                            .rotationEffect(Angle(degrees: -3.92))
                        if (viewState.giftedWishList.count > 0) {
                            Text("Что хочет твой малыш:")
                                .multilineTextAlignment(.leading)
                                .foregroundColor(.AppWhite)
                                .font(.custom("InriaSans-Bold", size: 18))
                                .frame(width: .infinity)
                                .padding(.horizontal, 16)
                                .padding(.vertical, 16)
                        }
                        ScrollView {
                            if (viewState.giftedWishList.count > 0) {
                                CustomFlexBoxView(alignment: .leading, spacing: 14.0, items: viewState.giftedWishList) {index in
                                    Text(viewState.giftedWishList[index])
                                    .foregroundColor(.AppRed)
                                    .font(.custom("Roboto-Medium", size: 16))
                                    .padding(.vertical, 8)
                                    .padding(.horizontal, 13)
                                    .background(Color.AppWhite.cornerRadius(20))
                                }
                                .padding(.leading, 16.0)
                            }
                        }.frame(height: .infinity)
                    }
                    Spacer()
                }
            }
           
            
        }
        .onAppear() {
            eventHandler(.FetchSantaInfo())
        }
    }
}


struct UserProfileScreen: View {
    let viewModel = SantaViewModel()
    var body: some View {
        ObservingView(statePublisher: statePublisher(viewModel.viewStates())) { viewState in
            UserView(viewState: viewState) { event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }
    }
}
