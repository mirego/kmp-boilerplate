import SwiftUI

extension View {
    func textStyle(_ size: TextSize, _ style: TextStyle, _ color: Color) -> some View {
        font(
            .custom(
                style.fontName,
                size: size.fontSize
            )
        )
        .foregroundColor(color)
        .limitDynamicFont()
    }

    func limitDynamicFont() -> some View {
        dynamicTypeSize(SwiftUI.DynamicTypeSize.xSmall ... SwiftUI.DynamicTypeSize.xxxLarge)
    }
}

enum TextSize {
    case largeTitle
    case title1
    case title2
    case title3
    case headline
    case body
    case callout
    case subHeadline
    case footnote
    case caption1
    case caption2

    var fontSize: CGFloat {
        switch self {
            case .largeTitle:
                return 34
            case .title1:
                return 28.0
            case .title2:
                return 22.0
            case .title3:
                return 20.0
            case .headline:
                return 18.0
            case .body:
                return 17.0
            case .callout:
                return 16.0
            case .subHeadline:
                return 15.0
            case .footnote:
                return 13.0
            case .caption1:
                return 12.0
            case .caption2:
                return 11.0
        }
    }
}

enum TextStyle: String {
    case light
    case regular
    case medium
    case semiBold
    case bold

    var fontName: String {
        switch self {
        case .light: return "HelveticaNeue-Light"
            case .regular: return "HelveticaNeue"
            case .medium: return "HelveticaNeue-Medium"
            case .semiBold: return "HelveticaNeue-Bold"
            case .bold: return "HelveticaNeue-CondensedBold"
        }
    }
}
