import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.data)
}

android {
    setNamespace("data.forbidden")
}

dependencies {
    // core
    implementation(projects.core.coroutine)

    // domain
    implementation(projects.domain.forbidden)
}
