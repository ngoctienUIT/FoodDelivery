import kotlin.collections.listOf

plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
}

android {
    namespace = "com.uit.food_delivery"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uit.food_delivery"
        versionCode = 1
        versionName = "1.0.0"
        minSdk = 26
        targetSdk = 33

        resourceConfigurations += listOf("vi", "en")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.5.2")
    implementation("androidx.compose.ui:ui-graphics:1.5.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.2")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.2")

    //appcompat
    implementation("androidx.appcompat:appcompat:1.7.0-alpha03")

    // toolbar
    implementation("me.onebone:toolbar-compose:2.3.2")

    //coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    //data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //hilt - dagger
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    ksp("com.google.dagger:hilt-compiler:2.48")

    //lib compose
    implementation("androidx.compose.foundation:foundation:1.5.2")
    implementation("androidx.navigation:navigation-compose:2.7.3")
    implementation("androidx.paging:paging-compose:3.2.1")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
//    implementation("androidx.compose.runtime:runtime-livedata:1.5.0-beta02")

//    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10"

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    //cloudinary
    implementation("com.cloudinary:cloudinary-android:2.3.1")
    implementation("com.cloudinary:cloudinary-android-download:2.3.1")
    implementation("com.cloudinary:cloudinary-android-preprocess:2.3.1")
}