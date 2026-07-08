import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.kindl.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
}

gradlePlugin {
    plugins {
        // app 모듈용
        register("androidApplication") {
            id = "kindl.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        // 일반 library용
        register("androidLibrary") {
            id = "kindl.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        // BuildConfig library용
        register("androidBuildConfig") {
            id = "kindl.android.buildconfig"
            implementationClass = "AndroidBuildConfigConventionPlugin"
        }
        // Compose 설정용
        register("androidCompose") {
            id = "kindl.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        // presentation(feature) 모듈용
        register("androidPresentation") {
            id = "kindl.android.presentation"
            implementationClass = "AndroidPresentationConventionPlugin"
        }
        // data 모듈용
        register("androidData") {
            id = "kindl.android.data"
            implementationClass = "AndroidDataConventionPlugin"
        }
        // domain 모듈용
        register("kotlinJvm") {
            id = "kindl.kotlin.jvm"
            implementationClass = "KotlinJvmConventionPlugin"
        }
        // Hilt 설정용
        register("hilt") {
            id = "kindl.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        // Serialization용
        register("serialization") {
            id = "kindl.serialization"
            implementationClass = "SerializationConventionPlugin"
        }
        // Test 설정용
        register("androidTest") {
            id = "kindl.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
    }
}
