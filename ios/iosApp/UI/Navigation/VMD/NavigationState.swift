import SwiftUI
import Shared

/*
    Since VMDNavigationManagerListener is a generic Objective-C class we must bind type parameters
    of VMDNavigationManagerListener to specific concrete types (VMDNavigationRoute).
    And this is why we have to cast the route to Screen in push and popTo methods
 */
class NavigationState<ScreenData, Route: VMDNavigationRoute>: VMDNavigationManagerListener<VMDNavigationRoute>, ObservableObject {
    let navigation: NavigationType<ScreenData>
    let route: Route?

    @Published var child: NavigationState<ScreenData, Route>?
    @Published var navigationDismissTriggered = false

    private let buildNavigation: (([Route], Route) -> NavigationType<ScreenData>)?
    private let navigationManager: VMDNavigationManager<Route>?

    private var lastNavigationDate: Date?

    init(
        navigation: NavigationType<ScreenData>,
        route: Route?,
        buildNavigation: (([Route], Route) -> NavigationType<ScreenData>)? = nil,
        navigationManager: VMDNavigationManager<Route>? = nil
    ) {
        self.navigation = navigation
        self.route = route
        self.buildNavigation = buildNavigation
        self.navigationManager = navigationManager

        super.init()
        navigationManager?.listener = self as? VMDNavigationManagerListener<Route>
    }

    override func push(route: VMDNavigationRoute) {
        guard let buildNavigation else { fatalError("buildNavigation not set")}
        guard let route = route as? Route else { fatalError("Invalid route type")}

        dedounceNavigation { [weak self] in
            guard let self else { return }
            lastNavigationDate = Date()
            top().child = NavigationState(navigation: buildNavigation(currentStack(), route), route: route)
        }
    }

    override func popTo(route: VMDNavigationRoute, included: Bool) {
        guard let route = route as? Route else { fatalError("Invalid route type") }

        dedounceNavigation { [weak self] in
            guard let self else { return }
            if let routeNavigationState = findLast(route: route) {
                if included {
                    findParent(state: routeNavigationState)?.child = nil
                } else {
                    routeNavigationState.child = nil
                }
            }
        }
    }

    override func pop() {
        dedounceNavigation { [weak self] in
            if let topPresenter = self?.topPresenter() {
                if let popDelayInSeconds = topPresenter.child?.navigation.popDelayInSeconds {
                    topPresenter.child?.navigationDismissTriggered = true
                    DispatchQueue.main.asyncAfter(deadline: .now() + popDelayInSeconds) {
                        topPresenter.child = nil
                    }
                } else {
                    topPresenter.child = nil
                }
            }
        }
    }

    private func dedounceNavigation(action: @escaping () -> Void) {
        let navigationAnimationDuration = 0.55

        if let lastNavigationDate {
            let timeIntervalSinceNow = -lastNavigationDate.timeIntervalSinceNow
            if timeIntervalSinceNow < navigationAnimationDuration {
                DispatchQueue.main.asyncAfter(deadline: .now() + (navigationAnimationDuration - timeIntervalSinceNow)) {
                    action()
                }
            } else {
                action()
            }
        } else {
            action()
        }
    }

    private func top() -> NavigationState<ScreenData, Route> {
        var current = self

        while current.child != nil {
            current = current.child!
        }
        return current
    }

    private func topPresenter() -> NavigationState<ScreenData, Route>? {
        guard self.child != nil else { return nil }

        var current = self
        while current.child?.child != nil {
            current = current.child!
        }
        return current
    }

    private func findLast(route: Route) -> NavigationState<ScreenData, Route>? {
        var current: NavigationState<ScreenData, Route>? = self
        var result: NavigationState<ScreenData, Route>?
        repeat {
            if current?.route?.uniqueId == route.uniqueId {
                result = current
            }
            current = current?.child
        } while current != nil

        return result
    }

    private func findParent(state: NavigationState<ScreenData, Route>) -> NavigationState<ScreenData, Route>? {
        var current = self
        while current.child != nil {
            if state === current.child {
                return current
            }
            current = current.child!
        }
        return nil
    }

    private func currentStack() -> [Route] {
        var stack: [Route] = []
        var current: NavigationState<ScreenData, Route>? = self
        while current != nil {
            if let route = current?.route {
                stack.append(route)
            }
            current = current?.child
        }
        return stack
    }
}
