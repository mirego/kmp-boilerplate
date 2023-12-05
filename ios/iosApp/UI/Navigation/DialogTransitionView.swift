import SwiftUI

struct DialogTransitionView<Content: View>: View {
    @ViewBuilder let content: () -> Content
    let tapAround: (() -> Void)?

    @State private var displayBackground = false
    @State private var displayContent = false
    @State private var dismissContent = false
    @Environment(\.navigationDismissTriggered) private var navigationDismissTriggered

    var body: some View {
        content()
            .scaleEffect(displayContent ? 1 : 0.1)
            .opacity(displayContent ? 1 : 0)
            .rotationEffect(Angle(degrees: displayContent ? 0 : 20))
            .offset(y: dismissContent ? UIScreen.main.bounds.height : 0)
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(
                Color.black.opacity(displayBackground ? 0.3 : 0.0)
                    .ignoresSafeArea()
                    .onTapGesture {
                        tapAround?()
                    }
            )
            .onAppear {
                withAnimation {
                    displayBackground = true
                }

                withAnimation(.spring(response: 0.4, dampingFraction: 0.7).delay(0.1)) {
                    displayContent = true
                }
            }
            .onChange(of: navigationDismissTriggered) { newValue in
                if newValue {
                    withAnimation(.easeIn(duration: 0.3)) {
                        dismissContent = true
                    }

                    withAnimation {
                        displayBackground = false
                    }
                }
            }
    }
}

#Preview {
    DialogTransitionView {
        VStack(spacing: 16) {
            Text("Hello")

            Text("world!")
        }
        .padding(.all, 32)
        .background(RoundedRectangle(cornerRadius: 16).fill(.white).shadow(color: .black.opacity(0.1), radius: 4))
        .padding(.horizontal, 32)
    } tapAround: {
        print("tap around!")
    }
}
