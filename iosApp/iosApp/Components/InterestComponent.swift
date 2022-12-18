//
//  InterestComponent.swift
//  iosApp
//
//  Created by Alex Ukenov on 18.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SharedSDK



struct InterestComponent: View {
    let viewState: UserState
    let eventHandler: (UserEvent) -> Void
    
    var body: some View {
        MainTemplate() {
            Spacer().frame(height: 40.0)
            Text("Скажи, что ты хочешь от санты?")
                .multilineTextAlignment(.center)
                .foregroundColor(.AppWhite)
                .font(.custom("InriaSans-Bold", size: 36))
                .padding(.horizontal, 16)
            Spacer().frame(height: 16.0)
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
            .frame(maxWidth: .infinity, alignment: .trailing)
            .padding(.trailing, 16.0)
            Spacer()
            ScrollView {
                if (viewState.wishes != nil && viewState.wishes!.count > 0) {
                    Text("Нажми для удаления")
                        .foregroundColor(.AppWhite)
                        .padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 10))
                        .font(.custom("Roboto-Regular", size: 16))
                    Spacer().frame(height: 16)
                    CustomFlexBoxView(alignment: .leading, spacing: 14.0, items: viewState.wishes!) {index in
                        Text(viewState.wishes![index].message).onTapGesture {
                            eventHandler(.RemoveWish(id: viewState.wishes![index].id))
                        }
                        .foregroundColor(.AppRed)
                        .font(.custom("Roboto-Medium", size: 16))
                        .padding(.vertical, 8)
                        .padding(.horizontal, 13)
                        .background(Color.AppWhite.cornerRadius(20))
                    }
                    .padding(.leading, 16.0)
                }
            }.frame(height: .infinity)
            Spacer()
            
        }
        .onAppear() {
            eventHandler(.GetUserInfo())
        }
    }
}
