fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android increment_version

```sh
[bundle exec] fastlane android increment_version
```

Increment version

### android uploadReleaseApk

```sh
[bundle exec] fastlane android uploadReleaseApk
```

This lane build the release APK and Distribute to Internal testers via Firebase

### android buildBundle

```sh
[bundle exec] fastlane android buildBundle
```

Build release app bundle

### android push_code

```sh
[bundle exec] fastlane android push_code
```

Push code to Github

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
