plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.kotlin.serialization)

	alias(libs.plugins.kotlinParcelize)
}

android {
	namespace = "com.websarva.wings.android.jan_scanner"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.websarva.wings.android.jan_scanner"
		minSdk = 34
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		debug {
			applicationIdSuffix = ".debug"
			isDebuggable = true
		}
		release {
			isMinifyEnabled = true
			applicationIdSuffix = ".release"
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
		viewBinding = true
		buildConfig = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	repositories {
	//	google()
	//	mavenCentral()
	}
}

dependencies {
	implementation(libs.kotlinStdlib)
	implementation(libs.kotlinxCoroutinesAndroid)
	implementation(libs.androidxCore)
	implementation(libs.androidxAppCompat)
	implementation(libs.androidxActivity)
	implementation(libs.androidxFragment)
	implementation(libs.androidxBrowser)
	implementation(libs.androidxWebkit)
	implementation(libs.androidxPreference)
	implementation(libs.androidxConstraintLayout)
	implementation(libs.bundles.androidxCamera)
	implementation(libs.mlkitBarcodeScanning)
	implementation(libs.material)
	implementation(libs.playReview)
	implementation(libs.playAppUpdate)
	implementation(libs.timber)

	//implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	//implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.navigation.compose)
	implementation(libs.kotlinx.serialization.json)

	implementation(libs.coilcompose)
	implementation(libs.leadingIconTab)

	testImplementation(libs.junit)
	//implementation(libs.material3)

	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)

	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	implementation(kotlin("script-runtime"))
}