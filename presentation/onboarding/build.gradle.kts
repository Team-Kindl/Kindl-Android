import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.presentation)
}

android {
    setNamespace("presentation.onboarding")
}

dependencies {
    // domain
    implementation(projects.domain.forbidden)
}
