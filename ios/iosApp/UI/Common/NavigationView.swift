import SwiftUI

private struct EmbedInNavigationViewModifier: ViewModifier {
    func body(content: Content) -> some View {
        NavigationView {
            content
                .navigationBarBackButtonHidden(true)
                .navigationBarTitleDisplayMode(.inline)
        }
        .navigationViewStyle(.stack)
    }
}

extension View {
    func embedInNavigationView() -> some View {
        modifier(EmbedInNavigationViewModifier())
    }
}
