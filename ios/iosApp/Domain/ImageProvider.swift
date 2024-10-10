import Shared
import SwiftUI
import Trikot

final class ImageProvider: VMDImageProvider {
    func imageForResource(imageResource: VMDImageResource) -> Image? {
        guard let imageResource = imageResource as? SharedImageResource else { return nil }
        switch imageResource {
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
