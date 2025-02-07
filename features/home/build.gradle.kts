plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("kotlin-kapt")
}
configurations.all {
    exclude(group = "com.intellij", module = "annotations")

    resolutionStrategy {
        force("org.jetbrains:annotations:23.0.0") // 🔥 Usa apenas a versão mais nova
    }
}
android {
    namespace = "com.st.home"
    compileSdk = 34

    defaultConfig {
        buildConfigField("String", "YOUTUBE_API_KEY", "\"${project.properties["YOUTUBE_API_KEY"]}\"")

        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeBom)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeToolingPreview)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleViewModelCompose)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.kotlinxSerializationsJson)

    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.koinAndroidXCompose)

    implementation(Dependencies.coil)

    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpLoggin)

    implementation(Dependencies.androidYTPlayer)

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.runtime.ktx)
    testImplementation(libs.junit)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.googleTruth)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.mockitoKt)
    testImplementation(Dependencies.mockitoOb)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
kapt {
    correctErrorTypes = true
}