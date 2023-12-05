import SwiftUI
import Shared

public extension View {
    func navigation<ScreenData, Route: VMDNavigationRoute, ScreenView: View>(
        navigationManager: VMDNavigationManager<Route>,
        @ViewBuilder buildView: @escaping (ScreenData) -> ScreenView,
        buildNavigation: @escaping ([Route], Route) -> NavigationType<ScreenData>
    ) -> some View {
        modifier(
            NavigationModifier<ScreenData, Route, ScreenView>(
                buildView: buildView,
                buildNavigation: buildNavigation,
                navigationManager: navigationManager
            )
        )
    }
}

private struct NavigationModifier<ScreenData, Route: VMDNavigationRoute, ScreenView: View>: ViewModifier {
    @StateObject private var rootState: NavigationState<ScreenData, Route>

    @ViewBuilder private let buildView: (ScreenData) -> ScreenView

    init(
        buildView: @escaping (ScreenData) -> ScreenView,
        buildNavigation: @escaping ([Route], Route) -> NavigationType<ScreenData>,
        navigationManager: VMDNavigationManager<Route>? = nil
    ) {
        self.buildView = buildView
        let rootNavigationState = NavigationState<ScreenData, Route>(
            navigation: NavigationType.root,
            route: nil,
            buildNavigation: buildNavigation,
            navigationManager: navigationManager
        )
        _rootState = StateObject(wrappedValue: rootNavigationState)
    }

    func body(content: Content) -> some View {
        NavigationContainerView(navigateState: rootState, buildView: buildView) {
            content
        }
    }
}
