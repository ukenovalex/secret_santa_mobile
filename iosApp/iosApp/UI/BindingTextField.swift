//
//  BindingTextField.swift
//  iosApp
//
//  Created by Alex Ukenov on 16.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct BindingTextField: View {
    private let currentValue: String
    @State private var value: String = ""
    private let label: String
    private let hint: String
    private let enabled: Bool
    private let isSecure: Bool
    private let onValueChanged: (String) -> Void
    
    init(
        currentValue: String,
        label: String,
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
        self.currentValue = currentValue
        self.value = currentValue
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
        .onChange(of: currentValue) { v in
            if (v != value) {
                value = v
            }
        }
        .onChange(of: value) { v in
            if (v.count - 1 == currentValue.count + 1) {
                value = currentValue
            }
        }

        .padding(EdgeInsets(top: 0, leading: 24, bottom: 0, trailing: 24))
        
    }
}
