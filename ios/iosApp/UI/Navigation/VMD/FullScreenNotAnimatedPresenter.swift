import SwiftUI
import UIKit

struct FullScreenNotAnimatedPresenter<Content: View>: UIViewRepresentable {
    let isPresented: Binding<Bool>
    var onDismiss: (() -> Void)?
    var content: () -> Content

    func makeCoordinator() -> Coordinator {
        Coordinator(presenter: self)
    }

    func makeUIView(context: Context) -> UIView {
        context.coordinator.view
    }

    func updateUIView(_: UIView, context: Context) {
        context.coordinator.presenter = self
    }

    class Coordinator: NSObject {
        private var parentView: AnyView?

        var presenter: FullScreenNotAnimatedPresenter {
            didSet {
                updateControllerLifecycle(
                    from: oldValue.isPresented.wrappedValue,
                    to: presenter.isPresented.wrappedValue
                )
            }
        }

        init(presenter: FullScreenNotAnimatedPresenter) {
            self.presenter = presenter
        }

        let view = UIView()

        private weak var controller: UIHostingController<Content>?

        private func updateControllerLifecycle(from oldValue: Bool, to newValue: Bool) {
            switch (oldValue, newValue) {
            case (false, true):
                presentController()
            case (true, false):
                dismissController()
            case (true, true):
                updateController()
            case (false, false):
                break
            }
        }

        private func presentController() {
            let controller = UIHostingController(rootView: presenter.content())
            controller.view.backgroundColor = .clear
            controller.modalPresentationStyle = .overFullScreen

            guard let presenting = view.owningController else {
                resetItemBinding()
                return
            }

            presenting.present(controller, animated: false)
            self.controller = controller
        }

        private func updateController() {
            guard let controller = controller else {
                return
            }

            controller.rootView = presenter.content()
        }

        private func dismissController() {
            guard let controller = controller else {
                return
            }

            controller.presentingViewController?.dismiss(animated: false)
            presenter.onDismiss?()
        }

        private func resetItemBinding() {
            presenter.isPresented.wrappedValue = false
        }
    }
}

extension UIView {
    var owningController: UIViewController? {
        if let responder = next as? UIViewController {
            return responder
        } else if let responder = next as? UIView {
            return responder.owningController
        } else {
            return nil
        }
    }
}
