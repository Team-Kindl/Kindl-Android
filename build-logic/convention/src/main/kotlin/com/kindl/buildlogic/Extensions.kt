package com.kindl.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

// build-logic Convention Plugin 안에서 libs.versions.toml에 접근하기 위한 확장함수
// 일반 build.gradle.kts에서는 libs가 자동 주입되지만
// Plugin 클래스 안(Convention Plugin 클래스 안에서는 build.gradle.kts와 달리)에서는 Project의 extension에서 직접 VersionCatalog를 꺼내는 확장함수가 필요
// 모든 Convention Plugin에서 libs.findLibrary(), libs.findVersion() 형태로 사용
val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * 모듈이 Application인지 Library인지 몰라도
 * 공통 타입인 CommonExtension으로 접근할 수 있게 해주는 확장함수
 *
 * 예를 들어 configureKotlinAndroid()는 Application/Library 둘 다 지원해야 하는데
 * 각각 타입이 달라서 CommonExtension으로 추상화해서 받 음
 *
 * Library → Application 순으로 시도하고 둘 다 없으면 에러 출력
 */
val Project.androidExtension: CommonExtension
    get() = runCatching { extensions.getByType<LibraryExtension>() as CommonExtension }
        .recoverCatching { extensions.getByType<ApplicationExtension>() as CommonExtension }
        .onFailure { println("Could not find Library or Application extension") }
        .getOrThrow()
