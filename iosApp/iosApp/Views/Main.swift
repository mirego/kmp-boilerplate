import SwiftUI
import shared

struct Main: View {

    @ObservedObject var screen = ObservableFlowWrapper<Screen>(MainRouter().screen, initial: Screen.Home())

    var body: some View {
        switch screen.value {
        case is Screen.Home:
            Home()
        default:
            fatalError("Unsupported screen: \(screen.value)")
        }
    }
}
