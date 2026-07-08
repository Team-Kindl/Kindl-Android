plugins {
    alias(libs.plugins.kindl.android.application)
    alias(libs.plugins.kindl.android.buildconfig)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kindl"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.kindl"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // core
    implementation(projects.core.network)

    // data
    implementation(projects.data.forbidden)

    // presentation
    implementation(projects.presentation.main)

    implementation(libs.timber)
    implementation(libs.androidx.appcompat)
    implementation(libs.coil.compose)
}
