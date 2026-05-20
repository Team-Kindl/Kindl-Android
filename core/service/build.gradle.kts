import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.library)
    alias(libs.plugins.kindl.android.compose)
}

android {
    setNamespace("core.service")
}
