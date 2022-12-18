//
//  CustomFlexBox.swift
//  iosApp
//
//  Created by Alex Ukenov on 18.12.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CustomFlexBoxView<Item, Content> : View where Item: Hashable, Content: View {
    let alignment: Alignment
    let spacing: CGFloat
    let items: [Item]
    let content: (Int) -> Content
    
    @State private var sizeBody: CGSize? = nil
    @State private var widthItems: [Item: CGFloat] = [:]
    
    init(alignment: Alignment = .center, spacing: CGFloat = 0, items: [Item], @ViewBuilder content: @escaping (Int) -> Content) {
        self.spacing = spacing
        self.alignment = alignment
        self.items = items
        self.content = content
    }
    
    var body: some View {
        self.contentView
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: self.alignment)
            .background(
                GeometryReader { (geo) in
                    Color.clear.onAppear {
                        self.sizeBody = geo.frame(in: .global).size
                    }
                }
            )
    }
    
    private var contentView: some View {
        VStack(alignment: self.alignment.horizontal, spacing: self.spacing) {
            ForEach(self.rowsIndices, id: \.self) { (row) in
                self.createRow(indices: row)
            }
        }
    }
    
    private func createRow(indices: [Int]) -> some View {
        HStack(alignment: self.alignment.vertical, spacing: self.spacing) {
            ForEach(indices, id: \.self) { (index) in
                Group {
                    self.content(index)
                }
                .background(
                    GeometryReader { (geo) in
                        Color.clear.onAppear {
                            self.widthItems[self.items[index]] = geo.frame(in: .global).size.width
                        }
                    }
                )
            }
        }
    }
    
    private var rowsIndices: [[Int]] {
        guard let widthBody = self.sizeBody?.width else {
            return self.items.indices.map { [ $0 ] }
        }
        var rowWidth: CGFloat = 0
        var rowItems: [Int] = []
        var rows: [[Int]] = []
        for index in 0 ..< items.count {
            if  let widthItem = self.widthItems[self.items[index]] {
                let rowWidthNext = rowWidth + widthItem + (rowItems.isEmpty ? 0 : self.spacing)
                if rowWidthNext <= widthBody {
                    rowItems.append(index)
                    rowWidth = rowWidthNext
                }
                else {
                    if rowItems.isEmpty == false {
                        rows.append(rowItems)
                        rowWidth = 0
                        rowItems = []
                    }
                    rowWidth = widthItem
                    rowItems = [ index ]
                }
            }
            else {
                if rowItems.isEmpty == false {
                    rows.append(rowItems)
                    rowWidth = 0
                    rowItems = []
                }
                rows.append([ index ])
            }
        }
        if rowItems.isEmpty == false {
            rows.append(rowItems)
            rowWidth = 0
            rowItems = []
        }
        return rows
    }
}
