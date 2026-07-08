import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.kindl.buildlogic.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * BuildConfig의 사용을 필요한 곳에서만 설정할 수 있도록 설정하는 Convention Plugin
 * 불필요한 buildconfig를 만드는 의존성을 분리하여 필요한 곳에서만 사용할 수 있게 구현
 * */
class AndroidBuildConfigConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // 현재 모듈이 App인지 Library인지 확인해서 알맞은 Extension을 가져옵니다
            val extension = extensions.findByType(ApplicationExtension::class.java)
                ?: extensions.findByType(LibraryExtension::class.java)
                ?: error("AndroidBuildConfigConventionPlugin은 안드로이드 애플리케이션 또는 라이브러리 플러그인이 적용된 이후에만 사용할 수 있습니다.")

            configureBuildTypes(extension as CommonExtension)
        }
    }
}
