import Shared
import SwiftUI

private struct PilotStyleModifier: ViewModifier {
    func body(content: Content) -> some View {
        content
            .environment(\.pilotImageProvider, ImageProvider())
    }
}

extension View {
    func pilotStyle() -> some View {
        modifier(PilotStyleModifier())
    }
}
