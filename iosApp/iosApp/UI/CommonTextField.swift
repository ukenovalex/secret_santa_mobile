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
    private let hint: String
    private let enabled: Bool
    private let isSecure: Bool
    private let onValueChanged: (String) -> Void
    
    init(hint: String, enabled: Bool = true, isSecure: Bool = false, onValueChanged: @escaping (String) -> Void) {
        self.hint = hint
        self.enabled = enabled
        self.isSecure = isSecure
        self.onValueChanged = onValueChanged
    }
    
    var body: some View {
        ZStack(alignment: .leading) {
            RoundedRectangle(cornerRadius: 10)
            
            if (value.isEmpty) {
                Text(hint)
                    .foregroundColor(Color.white)
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                    .font(.system(size: 16))
            }
            
            if isSecure {
                SecureField("", text: $value)
                    .foregroundColor(Color.white)
                    .font(.system(size: 16))
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .disabled(!enabled)
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                    .onChange(of: value) { newValue in
                        onValueChanged(newValue)
                    }
            } else {
                TextField("", text: $value)
                    .foregroundColor(Color.white)
                    .font(.system(size: 16))
                    .autocapitalization(.none)
                    .disabled(!enabled)
                    .disableAutocorrection(true)
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                    .onChange(of: value) { newValue in
                        onValueChanged(newValue)
                    }
            }
        }
        .frame(height: 56)
        .padding(EdgeInsets(top: 0, leading: 24, bottom: 0, trailing: 24))
    }
}

struct CommonTextField_Previews: PreviewProvider {
    static var previews: some View {
        CommonTextField(hint: "Your Email", enabled: true, isSecure: true, onValueChanged: { newValue in
            print(newValue)
        })
    }
}
