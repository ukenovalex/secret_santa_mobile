//
//  MainTemplate.swift
//  iosApp
//
//  Created by Slava Rykov on 03.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MainTemplate<Content: View>: View {
    @ViewBuilder var content: Content
    
    var body: some View {
        VStack(spacing: 0.0) {
            Rectangle()
                .frame(width: .infinity, height: 60)
                .foregroundColor(.white)
            VStack {
                content
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color.AppRed)
        }
        .ignoresSafeArea()
    }
    
    init(_ content: () -> Content) {
        self.content = content()
    }
}
