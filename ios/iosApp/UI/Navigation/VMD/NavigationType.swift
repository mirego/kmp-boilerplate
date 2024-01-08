import Foundation
import SwiftUI

public enum NavigationType<Screen, NavModifier: ViewModifier> {
    case root
    case push(screen: Screen, onDismiss: () -> Void)
    case sheet(screen: Screen, data: NavigationTypeData<NavModifier>)
    case fullScreenCover(screen: Screen, data: NavigationTypeData<NavModifier>)
    case fullScreenNotAnimated(screen: Screen, data: NavigationTypeData<NavModifier>, popDelayInSeconds: Double?)

    var screen: Screen? {
        switch self {
        case .root:
            return nil
        case .push(let screen, _):
            return screen
        case .sheet(let screen, _):
            return screen
        case .fullScreenCover(let screen, _):
            return screen
        case .fullScreenNotAnimated(let screen, _, _):
            return screen
        }
    }

    var popDelayInSeconds: Double? {
        switch self {
        case .fullScreenNotAnimated(_, _, let popDelayInSeconds):
            return popDelayInSeconds
        default:
            return nil
        }
    }
}

public struct NavigationTypeData<NavModifier: ViewModifier> {
    let embedInNavigationView: Bool
    let onDismiss: () -> Void
    let navigationViewModifier: NavModifier?

    init(embedInNavigationView: Bool, onDismiss: @escaping () -> Void, navigationViewModifier: NavModifier? = nil) {
        self.embedInNavigationView = embedInNavigationView
        self.onDismiss = onDismiss
        self.navigationViewModifier = navigationViewModifier
    }
}
