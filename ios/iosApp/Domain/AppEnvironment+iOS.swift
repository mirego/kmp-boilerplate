import Foundation
import Shared

extension AppEnvironment {
    private static let environmentKey = "Environment"

    static var current: AppEnvironment {
        loadCurrentEnvironment()
    }

    private static func persistCurrentEnvironment(_ environment: AppEnvironment) {
        UserDefaults.standard.set(environment.key, forKey: environmentKey)
        UserDefaults.standard.synchronize()
    }

    private static var defaultEnvironment: AppEnvironment {
        #if DEBUG
        return .dev
        #else
        return .production
        #endif
    }

    private static func loadCurrentEnvironment() -> AppEnvironment {
        if let environmentValue = UserDefaults.standard.string(forKey: environmentKey), let environment = environmentFromString(environmentValue) {
            return environment
        } else if let environmentValue = Bundle.main.infoDictionary?[environmentKey] as? String, let environment = environmentFromString(environmentValue) {
            return environment
        }
        return defaultEnvironment
    }

    private static func environmentFromString(_ stringValue: String) -> AppEnvironment? {
        switch stringValue.lowercased() {
        case AppEnvironment.dev.key:
            return .dev
        case AppEnvironment.production.key:
            return .production
        default:
            return nil
        }
    }
}
