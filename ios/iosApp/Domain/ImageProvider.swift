import Shared
import SwiftUI
import Trikot
import Pilot

final class ImageProvider: PilotImageProvider {
    func image(from resource: any PilotImageResource) -> Image {
        resource.image
    }
}

extension PilotImageResource {
    var image: Image {
        guard let self = self as? SharedImageResource else { fatalError("Unsupported image type")}
        return self.image
    }
}

extension SharedImageResource {
    var image: Image {
        switch self {
        case .emptyPageIcon:
            return Image(systemName: "questionmark.folder.fill")
        case .errorPageIcon:
            return Image(systemName: "exclamationmark.triangle.fill")
        case .imagePlaceholder:
            return Image(systemName: "photo")
        case .closeIcon:
            return Image(systemName: "xmark.circle.fill")
        }
    }
}
