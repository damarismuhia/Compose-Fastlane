# Automating an Application Deployment Process
> Fastlane is an excellent open-source tool based on Ruby, using which you can automate your Android or iOS app build & deployment.
> You can build, run tests, code signing, take screenshots, generate build files, upload to the app store and Play Store, and do many other tasks.

# Fastlane Setup 000000
- Fastlane depends on Ruby because it was built using the Ruby programming language and distributed via the Ruby gem ecosystem.
- You don’t need to manually install Ruby if you use package managers like Homebrew (macOS) or Chocolatey (Windows), as these handle Ruby installation for you.
  ** Installing fastlane *
    - fastlane can be installed in multiple ways. The preferred method is with Bundler.
    - fastlane can also be installed directly through Homebrew (if on macOS). It is possible to use macOS's system Ruby, but it's not recommended, as it can be hard to manage dependencies and cause conflicts.

To install fastlane using the bundler:
1. run: brew install rbenv - to install the rbenv. rbenv will help us to install ruby hence avoiding to use the system ruby. rbenv is one of the most popular ways to manage your Ruby environment in macos and linux.
2. run: rbenv init - after installing rbenv, we need to load rbenv into the shell
3. run: rbenv global 3.1.2 - sets Ruby 3.1.2 as the default Ruby version to be used system-wide on your machine unless you override it in a specific project directory.
4. run gem install bundler - to manage Ruby gems
5. Navigate to root directory of your project and create a Gemfile - touch Gemfile
    - A Gemfile is a text file where you specify all the external libraries or dependencies (called "gems") that your project needs to run. In this case, you'll specify Fastlane in the Gemfile.
    - A Gemfile typically includes:

        1. The source of gems (the default is usually RubyGems, but you can add custom sources).
        2. A list of gems your project needs, along with optional version constraints.
6. So inside the Gemfile add the following:
    - source "https://rubygems.org" - This line specifies the source from which Bundler will fetch the gems.
    - gem "fastlane"
7. Run: bundle install - to install the gems(in our case it will install fastlane) specified in your Gemfile. It also Creates a Gemfile.lock to record exactly which versions of the gems were installed, ensuring consistency.
8. bundle exec fastlane init - Initializes Fastlane for your project and creates a folder named fastlane in your project directory, with a:
    - Appfile - where you store project-specific details like app identifiers, package names
    - Fastfile - where we define different lanes and actions for automating tasks like building, testing, or distributing your app
9. bundle exec fastlane add_plugin firebase_app_distribution - creates a Plugin file. Inside this file is where we will add the required plugins for the bundler to install.(in our case: firebase_app_distribution that will help us upload builds)
    - In Addition to the created plugin file, the Gemfile gets modified with PluginFile path
    - Other plugins include: https://docs.fastlane.tools/plugins/available-plugins/
        1. bundle exec fastlane add_plugin versioning - Automatically increment version numbers in your build.gradle (Android) or Info.plist (iOS) files.
        2. bundle exec fastlane add_plugin testflight
        3. bundle exec fastlane add_plugin app_store_connect - Automate tasks such as managing app metadata, uploading screenshots, updating app versions, and more.

Ref: https://github.com/rbenv/rbenv?tab=readme-ov-file#installing-ruby-versions - installing ruby versions

NB:
- rbenv is one of the most popular Ruby version managers, and it's widely used for managing multiple Ruby versions on our local machines.
- It is recommended that you use Bundler and Gemfile to define your dependency on fastlane. This will clearly define the fastlane version to be used and its dependencies, and will also speed up fastlane execution.
- You should commit both the Gemfile and the Gemfile.lock to your Git repository. This ensures that:
    1. Others working on your project will automatically get the correct versions of the gems by running bundle install.
    2. Your CI/CD system will always have the right versions of gems installed when it runs. - If you don’t commit the Gemfile.lock file to version control, the CI system may install different versions of gems than what you’re using in your local environment, which could lead to unexpected bugs, failures, or other issues.
- bundle exec makes sure you're using the correct version of Fastlane that is defined in your project's Gemfile.
- The Pluginfile is a file in your fastlane directory where you specify additional Fastlane plugins that you want to use in your project. It's a place to list third-party Fastlane plugins (like firebase_app_distribution) that are not included by default in Fastlane.

# Building lanes:
- A lane is a workflow that Fastlane can complete. Lanes are defined in the Fastfile file.These lanes represent different release processes, and the actions are steps taken within each lane.
- * Steps: **
    1. Set the default platform - ios or android that our lane will be using.
    2. Next we need to tell Fastlane on which platform it needs to drive the lane.
    3. Give a name(using the lane keyword) and description(Using the desc keyword) to our lane.




# Android Build Types and Product Flavors
- They help us make changes in themes, app icons or adding different stages of the product such as dev, beta, production etc

## <b>Android Build Types</b>
- Build Types are sort of settings/properties that gradle plugin uses when building and packaging the app
1. Debug is the build type that is used when we run the application from the IDE directly onto a device.
2. A release is the build type that requires you to sign the APK.In the release build type, we obfuscate the code using ProGuard,DexGuard to prevent reverse engineering.

    ## SigningConfigs
   -  Gradle uses signingConfigs to define how the APKs or bundles are signed,
   - keystore is a critical part of your app's security, as it's used to sign your APKs and AABs.
   - To securely store Keystore info:
     1. Create Properties File (.properties) to Store your keystore details in a .properties file 
     2. Load Properties into your build.gradle.kts file.
     3. Configure the signing Options using the loaded properties

## Product Flavors
- product flavors are a way to create different versions of an app from the same codebase. The app version can be paid or free.
- The app can have different themes or texts, show or hide app functionalities or define different configurations for various environments (e.g., development, staging, production),
    creating multiple variants of an app (e.g., free vs. paid versions),
- Flavor Dimensions is a way to group flavors by a name.
- You can set the app name for each flavor on gradle using manifestPlaceholders attribute. eg. manifestPlaceholders["appLabel"] = "Compose(Dev)" then set android:label="${appLabel}" on the manifest file.

* Build Variants - is a combination of each product flavor with app build types.Each product flavor will be associated with app build types.
* Ref: https://medium.com/@badr.elattaoui/practical-guide-to-implement-product-flavors-in-android-step-by-step-7ca8b41ea8a0
* https://developer.android.com/build/build-variants#product-flavors
* https://proandroiddev.com/advanced-android-flavors-part-1-building-white-label-apps-on-android-ade16af23bcf