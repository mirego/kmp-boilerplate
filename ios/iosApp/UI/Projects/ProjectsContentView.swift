import Shared
import SwiftUI
import Trikot

struct ProjectsContentView: View {
    let viewModel: VMDListViewModel<ProjectsContentSection>

    private let padding: CGFloat = 16

    var body: some View {
        GeometryReader { proxy in
            ScrollView(.vertical) {
                VStack(spacing: padding * 2) {
                    ForEach(viewModel.elements, id: \.identifier) { section in
                        switch onEnum(of: section) {
                            case let .header(header):
                                headerView(header: header)
                            case let .noProjects(noProjects):
                                EmptyContentView(viewModel: noProjects.emptyViewModel)
                                    .padding(.top, 100)
                            case let .projectsList(projectsList):
                                projectListView(
                                    viewModel: projectsList.viewModel,
                                    itemSize: proxy.size.width - 2 * padding
                                )
                        }
                    }
                }
                .padding(.all, padding)
            }
        }
    }

    private func headerView(header: ProjectsContentSectionHeader) -> some View {
        VStack(spacing: padding * 2) {
            Text(header.title)
                .textStyle(.title1, .semiBold, .white)
                .lineLimit(1)
                .fixedSize(horizontal: false, vertical: true)

            Text(header.description_)
                .textStyle(.headline, .regular, .white)
                .fixedSize(horizontal: false, vertical: true)
                .multilineTextAlignment(.center)
        }
        .padding(.horizontal, padding)
    }

    private func projectListView(viewModel: VMDListViewModel<ProjectItem>, itemSize: CGFloat) -> some View {
        VStack(spacing: 16) {
            ForEach(viewModel.elements, id: \.identifier) { item in
                Button {
                    item.tapAction()
                } label: {
                    itemView(viewModel: item)
                        .frame(width: itemSize)
                }
            }
        }
    }

    private func itemView(viewModel: ProjectItem) -> some View {
        VStack(alignment: .leading, spacing: padding) {
            VMDImage(viewModel.image)
                .placeholder { imagePlaceHolder in
                    ZStack {
                        Rectangle()
                            .foregroundColor(Color(.primaryBlack))
                        imagePlaceHolder?
                            .resizable()
                            .scaledToFit()
                            .frame(width: 100, height: 100)
                            .foregroundStyle(Color.black)
                    }
                }
                .resizable()
                .scaledToFill()
                .clipShape(RoundedRectangle(cornerRadius: 16))
                .shadow(color: .white.opacity(0.05), radius: 6, y: 4)
            
            VStack(alignment: .leading, spacing: 0) {
                Text(viewModel.title)
                    .textStyle(.subHeadline, .regular, .white)
                    .lineLimit(1)
                    .fixedSize(horizontal: false, vertical: true)
                    
                Text(viewModel.subtitle)
                    .textStyle(.title1, .regular, .white)
                    .lineLimit(2)
                    .multilineTextAlignment(.leading)
                    .fixedSize(horizontal: false, vertical: true)
                    .padding(.top, 4)
                
                Text(viewModel.description_)
                    .textStyle(.caption1, .regular, Color(.accentOrange))
                    .lineLimit(2)
                    .multilineTextAlignment(.leading)
                    .fixedSize(horizontal: false, vertical: true)
                    .padding(.top, padding)
            }
            .padding([.horizontal, .bottom], padding)
            .redacted(reason: viewModel.isLoading ? .placeholder : [])
        }
        .background(Color(.primaryBlack))
    }
}
