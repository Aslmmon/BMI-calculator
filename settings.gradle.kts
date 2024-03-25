pluginManagement {
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

rootProject.name = "FitForm"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":features:paginatedMasterDetail")
include(":features:bmi_input")
include(":features:passwordGenerator")
include(":features:splash")
include(":core:designsystem")
include(":core:navigation")
