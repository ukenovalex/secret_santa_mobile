//
//  AppRoute.swift
//  iosApp
//
//  Created by Alex Ukenov on 15.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation


enum AppRoute: Equatable {

    // As swift not able to identify type of closure by default
    static func == (lhs: AppRoute, rhs: AppRoute) -> Bool {
        return lhs.key == rhs.key
    }

    case Welcome
    case Auth
    case AuthInterests
    case TabNavigator

    var key: String {
        switch self {
        case .Welcome:
            return "Welcome"
        case .Auth:
            return "Auth"
        case .AuthInterests:
            return "AuthInterests"
        case .TabNavigator:
            return "TabNavigator"
        }
    }
}
