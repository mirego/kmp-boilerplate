import Shared
import SwiftUI
import Trikot

final class ImageProvider: VMDImageProvider {
    func imageForResource(imageResource: VMDImageResource) -> Image? {
        guard let imageResource = imageResource as? SharedImageResource else { return nil }
        switch imageResource {
        case .emptypageicon:
            return Image(systemName: "questionmark.folder.fill")
        case .errorpageicon:
            return Image(systemName: "exclamationmark.triangle.fill")
        case .imageplaceholder:
            return Image(systemName: "photo")
        case .closeicon:
            return Image(systemName: "xmark.circle.fill")
        }
    }
}
