import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var greet = ObservableFlowWrapper<NSString>(Greeting().greeting(), initial: "initial")
    
	var body: some View {
        Text("\(greet.value)")
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
