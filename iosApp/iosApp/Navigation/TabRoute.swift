//
//  TabRoute.swift
//  iosApp
//
//  Created by Alex Ukenov on 16.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation


enum TabRoute: Equatable {

    // As swift not able to identify type of closure by default
    static func == (lhs: TabRoute, rhs: TabRoute) -> Bool {
        return lhs.key == rhs.key
    }

    case Profile
    case ProfileInterests
    case ProfileUsers


    var key: String {
        switch self {
        case .Profile:
            return "Profile"
        case .ProfileInterests:
            return "ProfileInterests"
        case .ProfileUsers:
            return "ProfileUsers"
        }
    }
}
