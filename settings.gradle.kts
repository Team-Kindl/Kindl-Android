pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Kindl"

include(":app")

// core
include(
    ":core:designsystem",
    ":core:common",
    ":core:navigation",
    ":core:network",
    ":core:localstorage",
    ":core:permission",
    ":core:service",
    ":core:work",
    ":core:coroutine"
)

// data
include(
    ":data:forbidden"
)

// domain
include(
    ":domain:forbidden"
)

// presentation
include(
    ":presentation:main",
    ":presentation:home"
)
