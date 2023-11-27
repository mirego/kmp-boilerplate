import Shared
import SwiftUI
import Trikot

struct ProjectDetailsContentView: View {
    let viewModel: ProjectDetailsRootContent
    private var textColor: Color {
        viewModel.textColor.color
    }

    var body: some View {
        GeometryReader { proxy in
            contentView
                .redacted(reason: viewModel.isLoading ? .placeholder : [])
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottomLeading)
                .background(
                    backgroundImageView
                        .frame(width: proxy.size.width, height: proxy.size.width * 1.25, alignment: .top),
                    alignment: .top
                )
        }
    }

    private var contentView: some View {
        VStack(alignment: .leading, spacing: 0) {
            Spacer(minLength: 0)
            Text(viewModel.title)
                .textStyle(.largeTitle, .bold, textColor)

            Text(viewModel.subtitle)
                .textStyle(.title1, .semiBold, textColor)
                .padding(.top, 24)

            textColor
                .frame(height: 1)
                .frame(maxWidth: .infinity)
                .padding(.top, 24)

            VStack(alignment: .leading, spacing: 4) {
                Text(viewModel.projectType.first)
                    .textStyle(.subHeadline, .semiBold, textColor)
                    .fixedSize(horizontal: false, vertical: true)
                
                Text(viewModel.projectType.second)
                    .textStyle(.subHeadline, .regular, textColor)
                    .lineLimit(2)
                    .multilineTextAlignment(.leading)
                    .fixedSize(horizontal: false, vertical: true)
            }
            .padding(.top, 32)

            VStack(alignment: .leading, spacing: 4) {
                Text(viewModel.releaseYear.first)
                    .textStyle(.subHeadline, .semiBold, textColor)
                    .fixedSize(horizontal: false, vertical: true)
                
                Text(viewModel.releaseYear.second)
                    .textStyle(.subHeadline, .regular, textColor)
                    .lineLimit(2)
                    .multilineTextAlignment(.leading)
                    .fixedSize(horizontal: false, vertical: true)
            }
            .padding(.top, 16)
        }
        .padding(.horizontal, 16)
    }

    private var backgroundImageView: some View {
        VMDImage(viewModel.image)
            .placeholder { imagePlaceHolder in
                ZStack {
                    Rectangle()
                        .foregroundColor(viewModel.backgroundColor.color)
                    imagePlaceHolder?
                        .resizable()
                        .scaledToFit()
                        .foregroundStyle(Color.black)
                        .frame(width: 100, height: 100)
                }
            }
            .resizable()
            .scaledToFit()
    }
}
