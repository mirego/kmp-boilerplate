import Foundation
import Shared

public class ObservableFlowWrapper<T: AnyObject>: ObservableObject {

    @Published public private(set) var value: T
    
    private var watcher: Closeable_?
    
    public init(_ flow: CFlow<T>, initial value: T) {
        self.value = value

        watcher = flow.watch { [weak self] t in
            self?.value = t
        }
    }
    
    deinit {
        watcher?.close()
    }
}
