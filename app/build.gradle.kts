import org.gradle.kotlin.dsl.androidTestImplementation
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.kaptAndroidTest
import org.gradle.kotlin.dsl.kaptTest
import org.gradle.kotlin.dsl.testImplementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20"
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android") version "2.48"



}

android {
    namespace = "com.example.randomfacts"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.randomfacts"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.ktx)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)

    implementation(libs.okhttp)

//    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)

    implementation ("com.google.dagger:hilt-android:2.56.2")
    kapt ("com.google.dagger:hilt-compiler:2.56.2")

    // For instrumentation tests
    androidTestImplementation  ("com.google.dagger:hilt-android-testing:2.56.2")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.56.2")

    // For local unit tests
    testImplementation ("com.google.dagger:hilt-android-testing:2.56.2")
    kaptTest ("com.google.dagger:hilt-compiler:2.56.2")
}
