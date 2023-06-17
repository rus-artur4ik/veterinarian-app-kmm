plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.rus_artur4ik.veterinarian"
        minSdk = 26
        targetSdk = 33
        versionCode = 3
        versionName = "0.3"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }

    packaging {
        resources.excludes.add("META-INF/INDEX.LIST")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    namespace = "com.rus_artur4ik.veterinarian"
}

dependencies {
    implementation(project(":shared"))

    // navigation
    implementation(project(":petCore"))
    implementation("androidx.navigation:navigation-compose:2.6.0")

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    // Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.material3:material3:1.2.0-alpha02")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0-alpha02")
    implementation("androidx.compose.animation:animation:1.4.3")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("com.google.android.material:compose-theme-adapter:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Dagger
    annotationProcessor("com.google.dagger:dagger-android-processor:2.43.1")
    annotationProcessor("com.google.dagger:dagger-compiler:2.44.2")
    implementation("com.google.dagger:dagger-android:2.43.1")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    // Misc
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.1-alpha")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.1-alpha")
}