//
//  MainTemplate.swift
//  iosApp
//
//  Created by Slava Rykov on 03.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import UIKit

struct MainTemplate<Content: View>: View {
    var content: () -> Content
    
    var body: some View {
        VStack(spacing: 0.0) {
            Rectangle()
                .frame(width: .infinity, height: 60)
                .foregroundColor(.white)
            VStack {
                content()
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color.AppRed)
        }
        .ignoresSafeArea()
        .onTapGesture {
            hideKeyboard()
        }
    }
    
    init(@ViewBuilder _ content: @escaping () -> Content) {
        self.content = content
    }
}

extension View {
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}
