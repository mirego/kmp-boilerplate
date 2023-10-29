import Shared
import SwiftUI
import Trikot

func previewi18N(languageCode: String) -> I18N {
    let i18N = DefaultI18N()
    KwordLoader.shared.setCurrentLanguageCode(i18N: i18N, basePaths: ["translation"], code: languageCode)
    return i18N
}

func factoryPreview(languageCode: String = "en") -> ViewModelFactoryPreview {
    let i18N = previewi18N(languageCode: languageCode)
    let usecaseFactory = UseCaseFactoryPreview()
    return ViewModelFactoryPreview(i18N: i18N, useCaseFactoryPreview: usecaseFactory)
}
