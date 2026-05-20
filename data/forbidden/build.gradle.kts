import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.data)
}

android {
    setNamespace("data.forbidden")
}

dependencies {
    implementation(projects.domain.forbidden)
}
