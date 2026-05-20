import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.library)
    alias(libs.plugins.kindl.hilt)
    alias(libs.plugins.kindl.serialization)
}

android {
    setNamespace("core.localstorage")
}

dependencies {
    //core
    implementation(projects.core.common)

    implementation(libs.javax.inject)
    implementation(libs.androidx.datastore.preferences)
}
