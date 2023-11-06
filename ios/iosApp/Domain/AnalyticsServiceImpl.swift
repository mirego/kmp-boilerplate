import FirebaseAnalytics
import Foundation
import Shared

public class AnalyticsServiceImpl: SharedAnalyticsService {

    public init(enableAnalytics: Bool = true) {
        isEnabled = enableAnalytics
    }

    public var isEnabled: Bool {
        didSet {
            Analytics.setAnalyticsCollectionEnabled(isEnabled)
        }
    }

    public func trackEvent(event: AnalyticsEvent, properties: [String: Any]) {
        Analytics.logEvent(event.name, parameters: properties)
    }
}
