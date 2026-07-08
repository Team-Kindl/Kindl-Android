package com.kindl.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * Compose 관련 설정 헬퍼
 * AndroidComposeConventionPlugin에서 호출
 *
 * Compose를 사용하는 모듈은 두 가지 필요
 * 1. buildFeatures.compose = true → Compose 컴파일러 활성화
 * 2. ComposeCompilerGradlePluginExtension → Compose Compiler 세부 옵션
 *
 * AGP 8.x부터는 kotlin.plugin.compose를 적용하면
 * buildFeatures.compose가 자동 활성화되기도 하지만
 * 명시적으로 선언해서 의도를 명확히 하는 게 좋을듯
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension
) {
    when (commonExtension) {
        is ApplicationExtension -> commonExtension.buildFeatures { compose = true }
        is LibraryExtension -> commonExtension.buildFeatures { compose = true }
    }

    extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
        // Compose 소스 정보를 컴파일 결과에 포함시켜
        // Layout Inspector에서 Composable 위치 추적이 가능해짐
        includeSourceInformation.set(true)
    }
}
