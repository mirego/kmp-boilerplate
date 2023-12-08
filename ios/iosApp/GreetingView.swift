import Shared
import SwiftUI

struct GreetingView: View {
    
    @ObservedObject var greet = ObservableFlowWrapper<NSString>(Greeting().greeting(), initial: "initial")
    
	var body: some View {
        Text("\(greet.value)")
	}
}

#Preview {
    PreviewContext {
        GreetingView()
    }
}
