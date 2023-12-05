import Foundation

public enum NavigationType<Screen> {
    case root
    case push(screen: Screen, onDismiss: (() -> Void))
    case sheet(screen: Screen, embedInNavigationView: Bool, onDismiss: (() -> Void))
    case fullScreenCover(screen: Screen, embedInNavigationView: Bool, onDismiss: (() -> Void))
    case fullScreenNotAnimated(screen: Screen, embedInNavigationView: Bool, onDismiss: (() -> Void), popDelayInSeconds: Double?)

    var screen: Screen? {
        switch self {
        case .root:
            return nil
        case .push(let screen, _):
            return screen
        case .sheet(let screen, _, _):
            return screen
        case .fullScreenCover(let screen, _, _):
            return screen
        case .fullScreenNotAnimated(let screen, _, _, _):
            return screen
        }
    }

    var popDelayInSeconds: Double? {
        switch self {
        case .fullScreenNotAnimated(_, _, _, let popDelayInSeconds):
            return popDelayInSeconds
        default:
            return nil
        }
    }
}
