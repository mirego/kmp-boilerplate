import Shared
import SwiftUI
import Trikot

extension View {
    func sheet<Route: VMDNavigationRoute, Content: View>(
        route: Route?,
        @ViewBuilder content: @escaping (Route) -> Content
    ) -> some View {
        sheet(
            item: Binding(value: route)
                .toIdentifiable(
                    didSet: { newValue in
                        if newValue == nil {
                            route?.resetBlock()
                        }
                    }
                ),
            content: { item in
                content(item.value)
                    // The scenePhase is not working when presenting a sheet or a fullScreenCover. With that modifier we pass it from the parent
                    .modifier(ScenePhaseModifier())
            }
        )
    }

    func fullScreen<Route: VMDNavigationRoute, Content: View>(
        route: Route?,
        @ViewBuilder content: @escaping (Route) -> Content
    ) -> some View {
        fullScreenCover(
            item: Binding(value: route)
                .toIdentifiable(
                    didSet: { newValue in
                        if newValue == nil {
                            route?.resetBlock()
                        }
                    }
                ),
            content: { item in
                content(item.value)
                    // The scenePhase is not working when presenting a sheet or a fullScreenCover. With that modifier we pass it from the parent
                    .modifier(ScenePhaseModifier())
            }
        )
    }

    func push<Route: VMDNavigationRoute, Content: View>(
        route: Route?,
        @ViewBuilder content: @escaping (Route) -> Content
    ) -> some View {
        background(
            NavigationLink(
                isActive: Binding(get: {
                    route != nil
                }, set: { newValue in
                    guard !newValue else {
                        return
                    }
                    route?.resetBlock()
                }),
                destination: {
                    if let route = route {
                        content(route)
                    }
                },
                label: {
                    EmptyView()
                }
            )
            .hidden()
            .accessibilityHidden(true)
        )
    }
}

struct ScenePhaseModifier: ViewModifier {
    @Environment(\.scenePhase) private var scenePhase

    func body(content: Content) -> some View {
        content
            .environment(\.scenePhase, scenePhase)
    }
}

struct NavigationRouteWrapper<Route: VMDNavigationRoute>: Equatable {
    static func == (lhs: NavigationRouteWrapper, rhs: NavigationRouteWrapper) -> Bool {
        lhs.route?.name == rhs.route?.name
    }

    let route: Route?
}
