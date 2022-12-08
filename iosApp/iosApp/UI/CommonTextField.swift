//
//  CommonTextField.swift
//  iosApp
//
//  Created by Alex Ukenov on 17.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CommonTextField: View {
    
    @State private var value: String = ""
    private let label: String
    private let hint: String
    private let enabled: Bool
    private let isSecure: Bool
    private let onValueChanged: (String) -> Void
    
    init(label: String,
         hint: String,
         enabled: Bool = true,
         isSecure: Bool = false,
         onValueChanged: @escaping (String) -> Void)
    {
        self.label = label
        self.hint = hint
        self.enabled = enabled
        self.isSecure = isSecure
        self.onValueChanged = onValueChanged
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(label)
                .font(.custom("Roboto-Medium", size: 16))
                .foregroundColor(.AppWhite)
                .frame(alignment: .leading)
                .padding(.bottom)
            ZStack(alignment: .leading) {
                if (value.isEmpty) {
                    Text(hint)
                        .foregroundColor(.AppWhite)
                        .padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 10))
                        .font(.custom("Roboto-Regular", size: 16))
                }
                
                if isSecure {
                    SecureField("", text: $value)
                        .foregroundColor(.AppWhite)
                        .font(.custom("Roboto-Medium", size: 16))
                        .autocapitalization(.none)
                        .disableAutocorrection(true)
                        .disabled(!enabled)
                        .padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 10))
                        .onChange(of: value) { newValue in
                            onValueChanged(newValue)
                        }
                } else {
                    TextField("", text: $value)
                        .foregroundColor(.AppWhite)
                        .font(.custom("Roboto-Medium", size: 16))
                        .autocapitalization(.none)
                        .disabled(!enabled)
                        .disableAutocorrection(true)
                        .padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 10))
                        .onChange(of: value) { newValue in
                            onValueChanged(newValue)
                        }
                }
            }
            .frame(height: 30)
            Divider().overlay(Color.AppWhite)
        }
        .padding(EdgeInsets(top: 0, leading: 24, bottom: 0, trailing: 24))
    }
}

struct CommonTextField_Previews: PreviewProvider {
    static var previews: some View {
        CommonTextField(label: "Name", hint: "Slacha", enabled: true, isSecure: true, onValueChanged: { newValue in
            print(newValue)
        })
    }
}
