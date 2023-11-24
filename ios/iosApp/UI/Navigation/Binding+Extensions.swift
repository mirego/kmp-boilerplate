import Shared
import SwiftUI

extension Binding {
    init(value: Value, didSet: ((Value) -> Void)? = nil) {
        self.init(get: { value }, set: { didSet?($0) })
    }

    func toIdentifiable<Wrapped: VMDNavigationRoute>(didSet: ((IdentifiableWrapper<Wrapped>?) -> Void)? = nil) -> Binding<IdentifiableWrapper<Wrapped>?> where Value == Wrapped? {
        .init(
            get: {
                if let wrappedValue = wrappedValue {
                    return IdentifiableWrapper(value: wrappedValue)
                } else {
                    return nil as IdentifiableWrapper<Wrapped>?
                }
            },
            set: { didSet?($0) }
        )
    }
}

struct IdentifiableWrapper<T: VMDNavigationRoute>: Identifiable {
    let value: T
    var id: String { value.name }
}
