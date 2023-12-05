import SwiftUI

struct NavigationDismissTriggeredKey: EnvironmentKey {
    static let defaultValue = false
}

public extension EnvironmentValues {
    var navigationDismissTriggered: Bool {
        get { self[NavigationDismissTriggeredKey.self] }
        set { self[NavigationDismissTriggeredKey.self] = newValue }
    }
}
