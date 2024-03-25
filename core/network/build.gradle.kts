plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)

    kotlin("kapt")



}

android {
    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"658680e409a5e9e11988f3e49361edae\"")
            buildConfigField ("String", "BASE_URL", "\"https://api.themoviedb.org\"")
        }
        release {
            buildConfigField("String", "API_KEY", "\"658680e409a5e9e11988f3e49361edae\"")
            buildConfigField ("String", "BASE_URL", "\"https://api.themoviedb.org\"")
        }
    }
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.ontop.core.network"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    defaultConfig {
        val apiKey = project.properties.get("MOVIEDB_KEY")
        buildConfigField("String", "API_KEY", apiKey.toString())
    }
    kotlinOptions {
        jvmTarget = "17"
    }


}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.hilt.android)
    api (libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    implementation (libs.retrofit.square)


}