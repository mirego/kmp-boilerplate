# kmp-boilerplate

| Section                                               | Description                                                     |
|-------------------------------------------------------|-----------------------------------------------------------------|
| [🎯 Objectives and context](#-objectives-and-context) | Project introduction and context                                |
| [🚧 Dependencies](#-dependencies)                     | Technical dependencies and how to install them                  |
| [🏎 Kickstart](#-kickstart)                           | Details on how to kickstart development on the project          |
| [🚀 Deploy](#-deployment)                             | Deployment details for platforms and environments               |
| [🏗 Code & architecture](#-code--architecture)        | Details on the application modules and technical specifications |
| [🔭 Possible improvements](#-possible-improvements)   | Possible code refactors, improvements and ideas                 |
| [🚑 Troubleshooting](#-troubleshooting)               | Recurring problems and proven solutions                         |

## 🎯 Objectives and context

…

### Supported platforms

| Platform | Version    |
|----------|------------|
| iOS      | ≥ 14.1     |
| Android  | ≥ 5.0 (21) |

## 🚧 Dependencies

Every runtime dependencies are defined in the `.tool-versions` file. These external tools and
dependencies are also required:

- [Android Studio Dolphin](https://developer.android.com/studio)
- [Xcode 14](https://developer.apple.com/xcode/)
  or [AppCode 2022.2](https://www.jetbrains.com/objc/)

## 🏎 Kickstart

### Initial setup

#### Android

1. Open the root folder using [Android Studio](https://developer.android.com/studio)
2. Run the app on your device or simulator 🚀

#### iOS

1. Open the [workspace](./ios/iosApp.xcworkspace) using [Xcode](https://developer.apple.com/xcode/)
   or [AppCode](https://www.jetbrains.com/objc/)
2. Specify your Development Team under the `Signing and Capabilities` tab of the `iosApp` target
3. Run the app on your device or simulator 🚀

### Tests

Tests can be ran with `./gradlew test`.

### Linting

Several linting and formatting tools can be ran to ensure coding style consistency:

- `./gradlew ktlintFormat` ensures Kotlin code follows our guidelines and best practices
- `cd ios && ./Pods/SwiftLint/swiftlint` ensures Swift code follows our guidelines and best
  practices

### Continuous integration

The `.github/workflows/ci.yaml` workflow ensures that the codebase is in good shape on each pull
request and branch push.

## 🚀 Deployment

### Versions & branches

…

## 🏗 Code & architecture

…

## 🔭 Possible improvements

| Description | Priority | Complexity | Ideas |
|-------------|----------|------------|-------|
| …           | …        | …          | …     |

## 🚑 Troubleshooting

…
