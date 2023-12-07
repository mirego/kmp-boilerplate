import Shared
import SwiftUI

struct PreviewContext<Content>: View where Content: View {
    let content: @MainActor (ViewModelFactory) -> Content

    let viewModelFactory = ViewModelFactory()

    var body: some View {
        content(viewModelFactory)
    }
}
