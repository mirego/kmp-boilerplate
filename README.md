<div align="center">
  <img src="https://user-images.githubusercontent.com/5982196/202266973-6cec2e9e-627d-4a97-a664-eef04d1e07d3.png" width="600" />
  <p><br />This repository is the stable base upon which we build our Kotlin Multiplatform projects at Mirego.<br />We want to share it with the world so you can build awesome multiplatform applications too.</p>
  <a href="https://github.com/mirego/kmp-boilerplate/actions/workflows/ci.yml"><img src="https://github.com/mirego/kmp-boilerplate/actions/workflows/ci.yaml/badge.svg"/></a>
  <a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/kotlin-1.9.10-blue.svg?logo=kotlin"/></a>
  <a href="https://opensource.org/licenses/BSD-3-Clause"><img src="https://img.shields.io/badge/License-BSD_3--Clause-blue.svg"/></a>
</div>

## Introduction

To learn more about _why_ we created and maintain this boilerplate project, read
our [blog post](https://shift.mirego.com/en/boilerplate-projects).

## Content

This boilerplate comes with batteries included, you’ll find:

- A brand new [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) project using
  [Kotlin 1.9.10](https://github.com/JetBrains/kotlin/releases/tag/v1.9.10) and
  the [Cocoapods Plugin](https://kotlinlang.org/docs/native-cocoapods.html)
- An Android app using [Jetpack Compose](https://developer.android.com/jetpack/compose)
- An iOS app using [SwiftUI](https://developer.apple.com/xcode/swiftui)
- Asynchronous & multithreading support
  with [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- Model serialization support
  with [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
- Opinionated Kotlin linting with [Ktlint](https://github.com/pinterest/ktlint)
- Opinionated Swift linting with [SwiftLint](https://github.com/realm/SwiftLint)
- A [CI workflow](.github/workflows/ci.yaml)
  using [GitHub actions](https://docs.github.com/en/actions)
- Translations with [Trikot.KWord](https://github.com/mirego/trikot/tree/master/trikot-kword)
  and [Accent](https://www.accent.reviews) (using a scheduled GitHub
  Actions [workflow](./.github/workflows/accent.yaml))
- A clean and useful `README.md` template (in [english](./BOILERPLATE_README.md))

## Usage

### With GitHub template

1. Click on the [**Use this template**](https://github.com/mirego/kmp-boilerplate/generate)
   button to create a new repository
2. Clone your newly created project (`git clone https://github.com/you/repo.git`)
3. Run the boilerplate setup script (`./boilerplate-setup.sh`)
4. Commit the changes (`git commit -a -m "Rename kmp-boilerplate parts"`)

### Without GitHub template

1. Clone this project (`git clone https://github.com/mirego/kmp-boilerplate.git`)
2. Delete the internal Git directory (`rm -rf .git`)
3. Run the boilerplate setup script (`./boilerplate-setup.sh`)
4. Create a new Git repository (`git init`)
5. Create the initial Git commit (`git commit -a -m "Initial commit using kmp-boilerplate"`)

### Building the project

* Run `asdf install` to install the dependencies described in `.tool-versions` on your system
* Make sure you have [Bundler](https://rubygems.org/gems/bundler) installed (`gem install bundler`)

#### Android

1. Install shared code specific gems at the root of the project (`bundle install`)
2. Open the root folder using [Android Studio](https://developer.android.com/studio)
3. Sync Gradle dependencies
4. Build and run the app on your device or simulator 🚀

#### iOS

1. Install iOS specific gems in the `/ios` folder (`cd ios && bundle install`)
2. Open the [workspace](./ios/iosApp.xcworkspace) using [Xcode](https://developer.apple.com/xcode/)
3. Specify your Development Team under the `Signing and Capabilities` tab of the `iosApp` target
4. Build and run the app on your device or simulator 🚀

## Preferred libraries

Some frequently used libraries aren’t included by default in this boilerplate since all projects
have their own needs and requirements. Here’s a list of our preferred libraries to help you get
started:

| Category                 | Libraries                                                                                                               |
|--------------------------|-------------------------------------------------------------------------------------------------------------------------|
| HTTP networking          | [`ktor`](https://ktor.io/)                                                                                              |
| GraphQL client           | [`apollo-kotlin`](https://www.apollographql.com/docs/kotlin/)                                                           |
| Persisted preferences    | [`multiplatform-setttings`](https://github.com/russhwolf/multiplatform-settings)                                        |
| I/O & File system        | [`okio`](https://square.github.io/okio/multiplatform/)                                                                  |
| Mocking (Unit tests)     | [`mockk`](https://mockk.io/)                                                                                            |
| Declarative UI framework | [`trikot-viewmodels-declarative-flow`](https://github.com/mirego/trikot/tree/master/trikot-viewmodels-declarative-flow) |
| Date & time              | [`kotlinx-datetime`](https://github.com/Kotlin/kotlinx-datetime)                                                        |

## OWASP Dependency-Check

[OWASP Dependency-Check](http://jeremylong.github.io/DependencyCheck/index.html) is installed as a Gradle plugin to scan your project to identify the use of known vulnerable components. It mainly checks for vulnerabilities in Gradle dependencies, but if [bundle-audit](https://github.com/rubysec/bundler-audit) is present on the system, it will also scan the Ruby Gems dependencies. It also has the capability to scan for Cocoapods/Swift Package Manager dependencies if executed on a macOS system.

## License

Kmp Boilerplate is © 2023 [Mirego](https://www.mirego.com) and may be freely distributed under
the [New BSD license](http://opensource.org/licenses/BSD-3-Clause). See
the [`LICENSE.md`](https://github.com/mirego/kmp-boilerplate/blob/master/LICENSE.md) file.

## About Mirego

[Mirego](https://www.mirego.com) is a team of passionate people who believe that work is a place
where you can innovate and have fun. We’re a team of [talented people](https://life.mirego.com) who
imagine and build beautiful Web and mobile applications. We come together to share ideas
and [change the world](http://www.mirego.org).

We also [love open-source software](https://open.mirego.com) and we try to give back to the
community as much as we can.
