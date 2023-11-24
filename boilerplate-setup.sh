#!/usr/bin/env sh

# -----------------------------------------------------------------------------
# Configuration
# -----------------------------------------------------------------------------

pascalCaseRegex="^[A-Z][a-z]+([A-Z][a-z]+)*$"
packageNameRegex="^([a-z]+\.){2}[a-z]+$"

# The identifiers above will be replaced in the content of the files found below
content=$(find . -type f \( \
  -name "*.xml" -or \
  -name "*.properties" -or \
  -name "*.kt" -or \
  -name "*.swift" -or \
  -name "*.gradle.kts" -or \
  -name "*.podspec" -or \
  -name "Podfile" -or \
  -name "*.plist" -or \
  -name "*.pbxproj" \
\) \
  -and ! -path "./build/*" \
  -and ! -path "./boilerplate-setup.sh" \
  -and ! -path "./.idea/*" \
  -and ! -path "./.git/*" \
  -and ! -path "./.gradle.kts/*" \
)

# -----------------------------------------------------------------------------
# Validation
# -----------------------------------------------------------------------------

isPascalCase() {
  if [[ "$1" =~ $pascalCaseRegex ]]; then
    argumentIsPascalCase=true
  else
    echo 'You must specify a name in PascalCase.'
  fi
}

isPackageNameValid() {
  if [[ $1 =~ $packageNameRegex ]]; then
    argumentIsPascalCase=true
  else
    echo 'You must specify a valid package name format (ex: com.example.project)'
  fi
}

# -----------------------------------------------------------------------------
# Helper functions
# -----------------------------------------------------------------------------

header() {
  echo "\033[0;33m▶ $1\033[0m"
}

success() {
  echo "\033[0;32m▶ $1\033[0m"
}

run() {
  echo ${@}
  eval "${@}"
}

# -----------------------------------------------------------------------------
# Execution
# -----------------------------------------------------------------------------

read -p $'\nWhat is your project name? (ex: MyProject)\n' PROJECT_NAME

argumentIsPascalCase=false
while [ ${argumentIsPascalCase} == "false" ]
do
  read -p $'\nWhat is the package name for this project? (ex: com.my.project)\n' USER_INPUT
  isPackageNameValid "$USER_INPUT"
done
PACKAGE_NAME=${USER_INPUT}

argumentIsPascalCase=false
while [ ${argumentIsPascalCase} == "false" ]
do
  read -p $'\nWhat is the name of the shared code\'s iOS framework? (ex: MyProject)\n' USER_INPUT
  isPascalCase "$USER_INPUT"
done
FRAMEWORK_NAME=${USER_INPUT}

header "Updating project name all files..."
projectName="kmp-boilerplate"
run "/usr/bin/sed -i .bak 's/$projectName/$PROJECT_NAME/g' settings.gradle.kts"
run "/usr/bin/sed -i .bak 's/$projectName/$PROJECT_NAME/g' shared/build.gradle.kts"
run "/usr/bin/sed -i .bak 's/$projectName/$PROJECT_NAME/g' ios/iosApp/Info.plist"
run "/usr/bin/sed -i .bak 's/$projectName/$PROJECT_NAME/g' BOILERPLATE_README.md"
success "Done!\n"

header "Updating iOS Framework name..."
frameworkName="Shared"
for file in $content; do
  if [[ $file != *.kt ]]; then
    run "/usr/bin/sed -i .bak s/$frameworkName/$FRAMEWORK_NAME/g $file"
  fi
done
mv shared/"${frameworkName}".podspec shared/"${FRAMEWORK_NAME}".podspec
success "Done!\n"

header "Updating project.pbxproj product bundle identifier..."
bundleIdentifier="com.mirego.kmp.boilerplate"
run "/usr/bin/sed -i .bak s/$bundleIdentifier/$PACKAGE_NAME/g ios/iosApp.xcodeproj/project.pbxproj"
success "Done!\n"

header "Replacing package name in all files..."
packageName="com.mirego.kmp.boilerplate"
for file in $content; do
  run "/usr/bin/sed -i .bak s/$packageName/$PACKAGE_NAME/g $file"
done
success "Done!\n"

header "Renaming folders..."
packageNameAsPath=${PACKAGE_NAME//\./\/}
for folder in $(find . -path '*/com/mirego/kmp/boilerplate*' -type d -and ! -path '*/generated/*' -and ! -path '*/build/*' -and ! -path '*/ios/*'); do
  newFolder="${folder//com\/mirego\/kmp\/boilerplate/$packageNameAsPath}"
  mkdir -p "$newFolder"
  cp -av "$folder/." "$newFolder/"
  rm -rf "$folder"
done
find "." -empty -type d -delete
success "Done!\n"

header "Cleaning bak files..."
  for file in $(find . -type f -name '*.bak'); do
    run rm "$file"
  done
success "Done!\n"

header "Replacing BOILERPLATE_README.md with project README.md..."
run rm -fr README.md && mv BOILERPLATE_README.md README.md
success "Done!\n"

header "Removing boilerplate license → https://choosealicense.com"
run rm -fr LICENSE.md
success "Done!\n"

header "Changing the Dependency-Check report format to HTML"
run sed -i '' 's/SARIF/HTML/' build.gradle.kts
success "Done!\n"

header "Removing boilerplate setup script..."
run rm -fr boilerplate-setup.sh
success "Done!\n"
