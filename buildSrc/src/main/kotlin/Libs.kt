object Libs {
    val androidLibs = listOf(
        "androidx.core:core-ktx:${Versions.core}",
        "androidx.appcompat:appcompat:${Versions.appCompat}",
        "com.google.android.material:material:${Versions.material}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}",
        "com.google.dagger:hilt-android:${Versions.hilt}",
        "androidx.recyclerview:recyclerview:${Versions.recyclerView}",
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}",
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}",
        "androidx.browser:browser:${Versions.browser}"
    )

    val androidKaptLibs = listOf(
        "com.google.dagger:hilt-compiler:${Versions.hilt}",
        "com.github.bumptech.glide:compiler:${Versions.glide}"
    )

    val kotlinLibs = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}",
        "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}",
    )

    val appTestLibs = listOf(
        "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    )

    val appTestKaptLibs = listOf(
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    )

    val libs = listOf(
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}",
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}",
        "com.github.bumptech.glide:glide:${Versions.glide}"
    )

    val testLibs = listOf(
        "junit:junit:${Versions.junit}",
        "androidx.arch.core:core-testing:${Versions.coreTesting}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}",
        "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}",
        "org.mockito:mockito-inline:${Versions.mockitoInline}"
    )

    val androidTestLibs = listOf(
        "androidx.test.espresso:espresso-contrib:${Versions.espresso}",
        "androidx.test.ext:junit-ktx:${Versions.junitKtx}",
    )
}