import SwiftUI

public extension View {
    func navigationRouteNameListener(routeNameUpdated: @escaping (String?) -> Void) -> some View {
        modifier(NavigationRouteNameListenerModifier(routeNameUpdated: routeNameUpdated))
    }
}

private struct NavigationRouteNameListenerModifier: ViewModifier {
    let routeNameUpdated: (String?) -> Void
    @Environment(\.presentedRouteName) private var presentedRouteName

    func body(content: Content) -> some View {
        content
            .onChange(of: presentedRouteName) { newRouteName in
                routeNameUpdated(newRouteName)
            }
    }
}
