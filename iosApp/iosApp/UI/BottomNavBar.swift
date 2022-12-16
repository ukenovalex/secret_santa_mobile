//
//  BottomNavBar.swift
//  iosApp
//
//  Created by Alex Ukenov on 16.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct BottomNavBar: View {
    let route: TabRoute
    let navToRoure: (_ newRoute: TabRoute) -> Void
    
    var body: some View {
        HStack {
            Spacer()
            Image("profile-nav-icon")
                .foregroundColor(route == .Profile ? .AppGreen : .AppRed)
                .onTapGesture {
                    navToRoure(.Profile)
                }
            Spacer()
            Image("interest-nav-icon")
                .foregroundColor(route == .ProfileInterests ? .AppGreen : .AppRed)
                .onTapGesture {
                    navToRoure(.ProfileInterests)
                }
            Spacer()
            Image("users-nav-icon")
                .foregroundColor(route == .ProfileUsers ? .AppGreen : .AppRed)
                .onTapGesture {
                    navToRoure(.ProfileUsers)
                }
            Spacer()
        }
        .frame(height: 50.0)
    }
}
