import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.library)
    alias(libs.plugins.kindl.hilt)
    alias(libs.plugins.kindl.serialization)
    alias(libs.plugins.kindl.android.buildconfig)
}

android {
    setNamespace("core.network")
}

dependencies {
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.timber)
}
