import com.android.build.api.dsl.ApplicationExtension
import com.kindl.buildlogic.configureKotlinAndroid
import com.kindl.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


/**
 * app 모듈 전용 Convention Plugin.
 * 프로젝트에서 오직 :app 모듈에서만 사용.
 *
 * Library와 다르게 Application은:
 * - targetSdk 설정 필요
 * - applicationId 설정 필요 (모듈 build.gradle에서 직접 설정)
 * - Compose 포함 (app도 Compose 사용)
 *
 * 적용되는 설정:
 * - com.android.application
 * - org.jetbrains.kotlin.plugin.compose
 * - compileSdk, minSdk, JVM 11 (configureKotlinAndroid 헬퍼)
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("kindl.hilt")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                // targetSdk는 compileSdk와 동일하게
                defaultConfig.targetSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()
                buildFeatures {
                    compose = true
                }
                packaging {
                    resources {
                        excludes.add("META-INF/**")
                    }
                }
            }
        }
    }
}
