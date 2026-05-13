import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.presentation)
}

android {
    setNamespace("presentation.main")

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //core
    implementation(projects.core.permission)
    implementation(projects.core.localstorage)

    //presentation
    implementation(projects.presentation.home)
}
