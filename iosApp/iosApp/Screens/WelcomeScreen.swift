//
//  WelcomeScreen.swift
//  iosApp
//
//  Created by Yegor Anisimov on 07.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct WelcomeView: View {
    var body: some View {
        NavigationView {
            ZStack {
                Image("welcome-image")
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .edgesIgnoringSafeArea(.all)
                VStack() {
                    Spacer()
                    Text("Ho Ho Ho! You little bitch!")
                        .font(.custom("Pacifico", size: 48))
                        .multilineTextAlignment(.center)
                        .rotationEffect(Angle(degrees: -5))
                        .padding(.bottom, 50)
                    NavigationLink(destination: AuthScreen(), label: <#T##() -> View#>)
                    Image(systemName: "hand.tap")
                        .font(.largeTitle)
                    Text("Tap Here")
                        .font(.custom("Pacifico", size: 24))
                }
                .foregroundColor(.AppRed)
                .padding(.horizontal, 24)
                .padding(.bottom, 64)
            }
        }
    }
}

struct WelcomeScreen: View {

    var body: some View {
        WelcomeView()
    }
}

class WelcomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        WelcomeScreen()
    }
}
