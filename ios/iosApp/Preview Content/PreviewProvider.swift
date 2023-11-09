import Shared
import SwiftUI

struct PreviewProvider<Content>: View where Content: View {
    let content: @MainActor (ViewModelFactory) -> Content
    let viewModelFactory = AppBootstrapper().viewModelFactory

    var body: some View {
        content(viewModelFactory)
    }
}
