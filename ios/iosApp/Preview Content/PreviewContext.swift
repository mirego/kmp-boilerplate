import Shared
import SwiftUI

struct PreviewContext<Content>: View where Content: View {
    let content: @MainActor () -> Content

    var body: some View {
        content()
    }
}
