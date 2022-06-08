import SwiftUI
import shared

struct Home: View {
    
    @ObservedObject var greet = ObservableFlowWrapper<NSString>(Greeting().greeting(), initial: "initial")
    
	var body: some View {
        Text("\(greet.value)")
	}
}

struct Home_Previews: PreviewProvider {
	static var previews: some View {
        Home()
	}
}
