object Dependencies {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.composeUi}" }
    val composeMaterial by lazy { "androidx.compose.material3:material3:${Versions.composeMaterial}" }
    val composeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.composeToolingPreview}" }
    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }

    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }
    val navigationCommon by lazy { "androidx.navigation:navigation-common-ktx:${Versions.navigationCommon}" }

    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.composeBom}" }
    val lifecycleViewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}" }
    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}" }

    val koinAndroid by lazy { "io.insert-koin:koin-android:${Versions.koinAndroid}" }
    val koinAndroidXCompose by lazy { "io.insert-koin:koin-androidx-compose:${Versions.koinAndroidXCompose}" }
    val koinCore by lazy { "io.insert-koin:koin-core::${Versions.koinAndroid}" }

    val kotlinxSerializationsJson by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}" }

    val googleAuth by lazy { "com.google.android.gms:play-services-auth:${Versions.googleAuth}" }
    val firebaseAuth by lazy { "com.google.firebase:firebase-auth-ktx:${Versions.firebaseAuth}" }

    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }

    val androidYTPlayer by lazy { "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.androidYTPlayer}" }

    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }

    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    val okhttpLoggin by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }

    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}" }
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val googleTruth by lazy { "com.google.truth:truth:${Versions.googleTruth}" }
    val mockito by lazy { "org.mockito:mockito-core:${Versions.mockito}" }
    val mockitoKt by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKt}" }
    val mockitoOb by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.mockito}" }


    val junit by lazy { "junit:junit:${Versions.junit}" }
    val androidJunit by lazy { "androidx.test.ext:junit:${Versions.androidJunit}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }

}

object Modules{
    const val auth = ":features:auth"
    const val main = ":features:home"
}