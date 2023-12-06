import SwiftUI
import Shared
import Trikot
import Kingfisher

struct Screen2View: View {
    @ObservedObject private var observableViewModel: ObservableViewModelAdapter<Screen2ViewModel>

    @Environment(\.navigationDismissTriggered) private var navigationDismissTriggered

    private let fromRect: CGRect

    @State private var displayContent = false

    init(viewModel: Screen2ViewModel, fromRect: CGRect) {
        self.fromRect = fromRect
        observableViewModel = viewModel.asObservable()
    }

    var viewModel: Screen2ViewModel {
        observableViewModel.viewModel
    }

    var body: some View {
        GeometryReader { proxy in
            VStack(spacing: 24) {
                KFImage(URL(string: "https://picsum.photos/600/600"))
                    .resizable()
                    .scaledToFill()
                    .frame(
                        width: displayContent ? proxy.size.width : fromRect.width,
                        height: displayContent ? proxy.size.width * 9 / 16 : fromRect.height
                    )
                    .clipped()
                    .allowsHitTesting(false)
                    .zIndex(2)

                VMDButton(viewModel.closeButton) {
                    Text($0.text)
                }
                .opacity(displayContent ? 1 : 0)
                .zIndex(1)

                VMDButton(viewModel.modalButton) {
                    Text($0.text)
                }
                .opacity(displayContent ? 1 : 0)
                .zIndex(1)

                Spacer()
            }
            .offset(
                x: 0,
                y: displayContent ? 0 : fromRect.minY
            )
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .ignoresSafeArea()
            .background(displayContent ? Color.white : Color.clear)
            .onAppear {
                withAnimation(.easeInOut(duration: 0.3)) {
                    displayContent = true
                }
            }
            .onChange(of: navigationDismissTriggered) { newValue in
                if newValue {
                    withAnimation(.easeInOut(duration: 0.3)) {
                        displayContent = false
                    }
                }
            }
        }
    }
}

#Preview {
    Screen2View(viewModel: factoryPreview().createScreen2(), fromRect: .zero)
}
