import Shared
import SwiftUI

extension VMDColor {
    var color: Color {
        Color(
            Color.RGBColorSpace.sRGB,
            red: Double(Double(red) / 255.0),
            green: Double(Double(green) / 255.0),
            blue: Double(Double(blue) / 255.0),
            opacity: Double(alpha)
        )
    }
}
