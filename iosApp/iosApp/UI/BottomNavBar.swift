//
//  BottomNavBar.swift
//  iosApp
//
//  Created by Alex Ukenov on 16.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import UIPilot

struct BottomNavBar: View {
    let route: AppRoute
    @EnvironmentObject var pilot: UIPilot<AppRoute>
    var body: some View {
        HStack {
            Spacer()
            Image("profile-nav-icon")
                .foregroundColor(route == .Profile ? .AppGreen : .AppRed)
                .onTapGesture {
                    pilot.push(.Profile)
                }
            Spacer()
            Image("interest-nav-icon")
                .foregroundColor(route == .ProfileInterests ? .AppGreen : .AppRed)
                .onTapGesture {
                    pilot.push(.ProfileInterests)
                }
            Spacer()
            Image("users-nav-icon")
                .foregroundColor(route == .ProfileUsers ? .AppGreen : .AppRed)
                .onTapGesture {
                    pilot.push(.ProfileUsers)
                }
            Spacer()
        }
        .frame(height: 50.0)
    }
}
