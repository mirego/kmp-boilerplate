import SwiftUI
import Shared

struct NavigationDismissTriggeredKey: EnvironmentKey {
    static let defaultValue = false
}

public extension EnvironmentValues {
    var navigationDismissTriggered: Bool {
        get { self[NavigationDismissTriggeredKey.self] }
        set { self[NavigationDismissTriggeredKey.self] = newValue }
    }
}

struct PresentedRouteNameKey: EnvironmentKey {
    static let defaultValue: String? = nil
}

public extension EnvironmentValues {
    var presentedRouteName: String? {
        get { self[PresentedRouteNameKey.self] }
        set { self[PresentedRouteNameKey.self] = newValue }
    }
}
