import SwiftUI

class NavigationTransitionDataState: ObservableObject {
    private let screen2Key = "screen2"

    @Published var data: [String: NavigationTransitionType] = [:]

    func screen2Data() -> CGRect? {
        if let screen2Data = data[screen2Key] {
            switch screen2Data {
            case .screen2(let rect):
                return rect
            }
        }
        return nil
    }

    func updateScreen2Data(_ rect: CGRect) {
        data[screen2Key] = NavigationTransitionType.screen2(rect)
    }
}

struct NavigationTransitionData: EnvironmentKey {
    static let defaultValue: Binding<[String: NavigationTransitionType]> = .constant([:])
}

extension EnvironmentValues {
    var navigationTransitionData: Binding<[String: NavigationTransitionType]> {
        get { self[NavigationTransitionData.self] }
        set { self[NavigationTransitionData.self] = newValue }
    }
}

enum NavigationTransitionType: Equatable {
    case screen2(CGRect)
}
