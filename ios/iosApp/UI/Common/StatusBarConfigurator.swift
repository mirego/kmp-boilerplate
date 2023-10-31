import UIKit
import SwiftUI

struct StatusBarConfiguratorKey: EnvironmentKey {
    static let defaultValue = StatusBarConfigurator()
}

extension EnvironmentValues {
  var statusBarConfigurator: StatusBarConfigurator {
    get { self[StatusBarConfiguratorKey.self] }
    set { self[StatusBarConfiguratorKey.self] = newValue }
  }
}

class StatusBarConfigurator: ObservableObject {

    private var window: UIWindow?

    var statusBarStyle: UIStatusBarStyle = .darkContent {
        didSet {
            if statusBarStyle == .lightContent {
                window?.makeKeyAndVisible()
            } else {
                window?.isHidden = true
            }
            window?.rootViewController?.setNeedsStatusBarAppearanceUpdate()
        }
    }

    func prepare(scene: UIWindowScene) {
        if window == nil {
            let window = UIWindow(windowScene: scene)
            let viewController = StatusBarConfiguratorViewController()
            viewController.configurator = self
            window.rootViewController = viewController
            window.frame = UIScreen.main.bounds
            window.alpha = 0
            self.window = window
        }
        window?.windowLevel = .statusBar
        window?.makeKeyAndVisible()
    }

    fileprivate class StatusBarConfiguratorViewController: UIViewController {
        weak var configurator: StatusBarConfigurator!
        override var preferredStatusBarStyle: UIStatusBarStyle { configurator.statusBarStyle }
    }
}

struct SceneFinder: UIViewRepresentable {

    var getScene: ((UIWindowScene) -> Void)?

    func makeUIView(context: Context) -> SceneFinderView {
        SceneFinderView()
    }

    func updateUIView(_ uiView: SceneFinderView, context: Context) {
        uiView.getScene = getScene
    }

    class SceneFinderView: UIView {
        var getScene: ((UIWindowScene) -> Void)?

        override func didMoveToWindow() {
            if let scene = window?.windowScene {
                getScene?(scene)
            }
        }
    }
}

extension View {
    func prepareStatusBarConfigurator(_ configurator: StatusBarConfigurator) -> some View {
        background(SceneFinder { scene in
            configurator.prepare(scene: scene)
        })
    }
}
