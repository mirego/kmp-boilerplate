import SwiftUI
import Shared

public extension View {
    func navigation<ScreenData, Route: VMDNavigationRoute, Action: AnyObject, ScreenView: View, NavModifier: ViewModifier>(
        navigationManager: VMDNavigationManager<Route, Action>,
        @ViewBuilder buildView: @escaping (ScreenData) -> ScreenView,
        buildNavigation: @escaping ([Route], Route) -> NavigationType<ScreenData, NavModifier>,
        handleAction: ((Action) -> Void)? = nil
    ) -> some View {
        modifier(
            NavigationModifier<ScreenData, Route, Action, ScreenView, NavModifier>(
                buildView: buildView,
                buildNavigation: buildNavigation,
                handleAction: handleAction,
                navigationManager: navigationManager
            )
        )
    }
}

private struct NavigationModifier<ScreenData, Route: VMDNavigationRoute, Action: AnyObject, ScreenView: View, NavModifier: ViewModifier>: ViewModifier {
    @StateObject private var rootState: NavigationState<ScreenData, Route, Action, NavModifier>

    @ViewBuilder private let buildView: (ScreenData) -> ScreenView

    init(
        buildView: @escaping (ScreenData) -> ScreenView,
        buildNavigation: @escaping ([Route], Route) -> NavigationType<ScreenData, NavModifier>,
        handleAction: ((Action) -> Void)? = nil,
        navigationManager: VMDNavigationManager<Route, Action>? = nil
    ) {
        self.buildView = buildView
        let rootNavigationState = NavigationState<ScreenData, Route, Action, NavModifier>(
            navigation: NavigationType.root,
            route: nil,
            buildNavigation: buildNavigation,
            handleAction: handleAction,
            navigationManager: navigationManager
        )
        _rootState = StateObject(wrappedValue: rootNavigationState)
    }

    func body(content: Content) -> some View {
        NavigationContainerView(navigateState: rootState, buildView: buildView) {
            content
        }
        .onAppear {
            rootState.startListening()
        }
    }
}
