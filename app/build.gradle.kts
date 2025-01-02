import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}
val keyStoreProperties = Properties()
val keyStorePropertiesFile = rootProject.file("signingConfigs/keystore.properties") //Loads the signing.properties file from the root of the project.
if (keyStorePropertiesFile.exists()) {
    keyStoreProperties.load(FileInputStream(keyStorePropertiesFile)) //Loads the keystore details from the file into a Properties object.
}


android {

    namespace = "com.dmuhia.composefastlane"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dmuhia.composefastlane"
        minSdk = 24
        targetSdk = 35
        versionCode = 2
        versionName = "0.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("release") {
            storePassword = keyStoreProperties["storePassword"].toString()
            keyAlias = keyStoreProperties["keyAlias"].toString()
            keyPassword = keyStoreProperties["keyPassword"].toString()
            storeFile = keyStoreProperties["storeFile"]?.let { file(it) } ?: throw GradleException("Keystore file not found!")

        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs["release"]
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        /**
         * The `initWith` property lets you copy configurations from other build types,
         * then configure only the settings you want to change. This one copies the debug build
         * type, and then changes the versionNameSuffix.
         */
       create( "uat"){
            initWith(getByName("debug"))
           versionNameSuffix = ".uat"
        }
    }
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = ".dev"
            manifestPlaceholders["appLabel"] = "Compose(Dev)"
            resValue("string", "feature", "Basic Features") //resValue in build.gradle: Defines resources dynamically at build time.
        }
        create("prod") {
            dimension = "environment"
            manifestPlaceholders["appLabel"] = "Compose"
            resValue("string", "feature", "Premium Features")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    sourceSets {
        getByName("dev") {
            java {
                srcDirs("src/dev/java")
            }
        }
        getByName("prod") {
            java {
                srcDirs("src/prod/java")
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
