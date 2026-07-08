import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.presentation)
}

android {
    setNamespace("presentaion.splash")
}

dependencies {
    // core
    implementation(projects.core.network)
    implementation(projects.core.localstorage)
}
