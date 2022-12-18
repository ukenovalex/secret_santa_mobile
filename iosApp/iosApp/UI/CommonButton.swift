//
//  CommonButton.swift
//  iosApp
//
//  Created by Slava Rykov on 03.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CommonButton: View {
    private let label: String
    private let disabled: Bool
    private let action: () -> Void

    init(label: String,
         disabled: Bool = false,
         action: @escaping () -> Void)
    {
        self.label = label
        self.disabled = disabled
        self.action = action
    }
    
    var body: some View {
        Button(action: action, label: {
            Text(label)
                .font(.custom("Roboto-Bold", size: 20))
        })
            .foregroundColor(.AppWhite)
            .padding(.vertical, 10)
            .padding(.horizontal, 50)
            .background(disabled ? Color.AppGray.cornerRadius(10) : Color.AppGreen.cornerRadius(10))
            .disabled(disabled)
    }
}

struct CommonButton_Previews: PreviewProvider {
    static var previews: some View {
        CommonButton(label: "123", disabled: false, action: {print(123)})
    }
}
