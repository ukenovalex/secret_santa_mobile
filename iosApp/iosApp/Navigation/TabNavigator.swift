//
//  TabNavigator.swift
//  iosApp
//
//  Created by Alex Ukenov on 16.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI



struct TabNavigator: View {
    @State var currentRoute = TabRoute.Profile
    
    func navToRoute(route: TabRoute) {
        currentRoute = route
    }
    
    var body: some View {
        switch currentRoute {
        case .Profile: UserProfileScreen()
        case .ProfileUsers: ProfileUsersScreen()
        case .ProfileInterests: ProfileInterestsScreen()
        }
        BottomNavBar(route: currentRoute) {newRoute in
            navToRoute(route: newRoute)
        }
    }
}
