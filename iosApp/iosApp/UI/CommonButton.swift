//
//  CommonButton.swift
//  iosApp
//
//  Created by Slava Rykov on 03.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CommonButton: View {
    let action: () -> Void
    var disabled: Bool = false
    let label: () -> Text
    
    var body: some View {
        Button(action: action, label: label)
            .foregroundColor(.AppRed)
            .background(Color.AppWhite)
    }
}
