package com.kindl.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import java.util.Properties

/**
 * Build Type을 세팅 헬퍼 - ApplicationExtension, LibraryExtension 공통
 * */
fun Project.configureBuildTypes(
    commonExtension: CommonExtension,
) {
    val properties = Properties().apply {
        // 루트 디렉토리의 local.properties 파일을 읽어옵니다
        project.rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
    }

    fun configureBuildTypeFields(
        buildConfigField: (String, String, String) -> Unit,
        isDebug: Boolean
    ) {
        // debug 환경이면 "dev", release 환경이면 "prod" 접두사를 사용합니다
        val prefix = if (isDebug) "dev" else "prod"

        buildConfigField(
            "String",
            "BASE_URL",
            properties.getQuotedProperty("$prefix.base.url")
        )
    }

    commonExtension.apply {
        when (this) {
            is ApplicationExtension -> {
                buildFeatures { buildConfig = true }
                buildTypes {
                    getByName("debug") { configureBuildTypeFields(::buildConfigField, true) }
                    getByName("release") { configureBuildTypeFields(::buildConfigField, false) }
                }
            }

            is LibraryExtension -> {
                buildFeatures { buildConfig = true }
                buildTypes {
                    getByName("debug") { configureBuildTypeFields(::buildConfigField, true) }
                    getByName("release") { configureBuildTypeFields(::buildConfigField, false) }
                }
            }
        }
    }
}

// local.properties에서 값을 가져올 때, 양끝에 따옴표("")가 없으면 자동으로 붙여주는 안전 장치입니다
private fun Properties.getQuotedProperty(key: String): String {
    val value = getProperty(key) ?: ""
    if (value.startsWith("\"") && value.endsWith("\"")) {
        return value
    }
    return "\"$value\""
}
