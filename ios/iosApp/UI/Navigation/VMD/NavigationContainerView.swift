import SwiftUI
import Shared

struct NavigationContainerView<ScreenData, Route: VMDNavigationRoute, Action: AnyObject, Content: View, ScreenView: View>: View {
    @ObservedObject var navigateState: NavigationState<ScreenData, Route, Action>

    @ViewBuilder let buildView: (ScreenData) -> ScreenView
    let content: () -> Content

    var body: some View {
        if embedInNavigationView {
            if #available(iOS 16, *) {
                NavigationStack {
                    unwrappedBody
                }
            } else {
                NavigationView {
                    unwrappedBody
                }
                .navigationViewStyle(.stack)
            }
        } else {
            unwrappedBody
        }
    }

    @ViewBuilder
    private var unwrappedBody: some View {
        content()
            .sheet(
                isPresented: sheetBinding,
                onDismiss: childOnDismiss,
                content: { childView }
            )
            .fullScreenCover(
                isPresented: fullScreenCoverBinding,
                onDismiss: childOnDismiss,
                content: { childView }
            )
            .backportNavigationLink(isPresented: pushBinding) {
                childView
            }
            .background(
                FullScreenNotAnimatedPresenter(
                    isPresented: fullScreenNotAnimatedBinding,
                    onDismiss: childOnDismiss,
                    content: { childView }
                )
            )
            .environment(\.navigationDismissTriggered, navigateState.navigationDismissTriggered)
    }

    private var embedInNavigationView: Bool {
        switch navigateState.navigation {
        case .root:
            return false
        case .push:
            return false
        case .sheet(_, let embedInNavigationView, _):
            return embedInNavigationView
        case .fullScreenCover(_, let embedInNavigationView, _):
            return embedInNavigationView
        case .fullScreenNotAnimated(_, let embedInNavigationView, _, _):
            return embedInNavigationView
        }
    }

    @ViewBuilder
    private var childView: some View {
        if let child = navigateState.child, let screen = child.navigation.screen {
            NavigationContainerView<ScreenData, Route, Action, ScreenView, ScreenView>(navigateState: child, buildView: buildView) {
                buildView(screen)
            }
        } else {
            EmptyView()
        }
    }

    private var isActiveBinding: Binding<Bool> {
        Binding(
            get: {
                navigateState.child != nil
            },
            set: { isShowing in
                if !isShowing {
                    if isChildPush {
                        childOnDismiss?()
                    }
                    navigateState.child = nil
                }
            }
        )
    }

    private var sheetBinding: Binding<Bool> {
        guard let child = navigateState.child else { return .constant(false) }
        switch child.navigation {
        case .sheet:
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var fullScreenCoverBinding: Binding<Bool> {
        guard let child = navigateState.child else { return .constant(false) }
        switch child.navigation {
        case .fullScreenCover:
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var fullScreenNotAnimatedBinding: Binding<Bool> {
        guard let child = navigateState.child else { return .constant(false) }
        switch child.navigation {
        case .fullScreenNotAnimated:
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var pushBinding: Binding<Bool> {
        guard let child = navigateState.child else { return .constant(false) }
        switch child.navigation {
        case .push:
            return isActiveBinding
        default:
            return .constant(false)
        }
    }

    private var childOnDismiss: (() -> Void)? {
        guard let child = navigateState.child else { return nil }
        switch child.navigation {
        case .root:
            return nil
        case .push(_, let onDismiss):
            return onDismiss
        case .sheet(_, _, let onDismiss):
            return onDismiss
        case .fullScreenCover(_, _, let onDismiss):
            return onDismiss
        case .fullScreenNotAnimated(_, _, onDismiss: let onDismiss, _):
            return onDismiss
        }
    }

    private var isChildPush: Bool {
        guard let child = navigateState.child else { return false }
        switch child.navigation {
        case .push:
            return true
        default:
            return false
        }
    }
}

private extension View {
    @ViewBuilder
    func backportNavigationLink<V>(isPresented: Binding<Bool>, @ViewBuilder destination: () -> V) -> some View where V: View {
        if #available(iOS 16, *) {
            navigationDestination(isPresented: isPresented, destination: destination)
        } else {
            background(
                NavigationLink(destination: destination(), isActive: isPresented, label: EmptyView.init)
                    .hidden()
            )
        }
    }
}
