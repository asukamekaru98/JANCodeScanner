pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
		gradlePluginPortal().content {
			includeGroupAndSubgroups("com.github.ben-manes")
		}
		mavenCentral()
		//gradlePluginPortal()
	}
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google().content {
			includeGroupAndSubgroups("com.android")
			includeGroupAndSubgroups("com.google")
			includeGroupAndSubgroups("androidx")
		}
		mavenCentral()
	}
}

rootProject.name = "JAN_Scanner"
include(":app")
 