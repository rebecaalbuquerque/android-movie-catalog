object Libraries {

    object Kotlin {

        object Coroutines {
            private const val version = "1.4.2"

            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"

            object Test {
                const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"
            }
        }

    }

    object AndroidX {

        object View {
            private const val constraintLayoutVersion = "2.0.2"
            private const val navigationVersion = "2.0.2"

            const val core = "androidx.core:core-ktx:1.3.1"
            const val constraintlayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
            const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
            const val recyclerViewSolver = "androidx.constraintlayout:constraintlayout-solver:$constraintLayoutVersion"
            const val cardView = "androidx.cardview:cardview:1.0.0"
            const val appCompat = "androidx.appcompat:appcompat:1.2.0"
            const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
            const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
            const val legacySupport = "androidx.legacy:legacy-support-v13:1.0.0"

        }

        object Lifecycle {
            private const val version = "2.2.0"

            const val self = "androidx.lifecycle:lifecycle-extensions:$version"
            const val runtime =  "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val common =  "androidx.lifecycle:lifecycle-common-java8:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        }

        object Test {
            const val runner = "androidx.test:runner:1.2.0"
            const val junit =  "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
            const val core = "androidx.test:core:1.0.0"
            const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        }
    }

    object Google {

        object MaterialComponents {
            // releases (https://maven.google.com/web/index.html#com.google.android.material:material)
            const val self = "com.google.android.material:material:1.2.1"
        }

        object Gson {
            // releases (https://github.com/google/gson/releases)
            const val self = "com.google.code.gson:gson:2.8.2"
        }

        object Firebase {
            // releases (https://maven.google.com/web/index.html#com.google.firebase:firebase-crashlytics)
            const val crashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"
            const val perf = "com.google.firebase:firebase-perf:19.0.8"
            const val analytics = "com.google.firebase:firebase-analytics:17.5.0"
            // releases (https://maven.google.com/web/index.html#com.google.firebase:firebase-analytics-ktx)
            const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx:17.6.0"
        }

    }

    object Room {
        const val runtime = "androidx.room:room-runtime:2.2.5"
        const val compiler = "androidx.room:room-compiler:2.2.5"
        const val kxt = "androidx.room:room-ktx:2.2.5"
    }

    object DI {
        // releases (https://github.com/InsertKoinIO/koin/releases)
        private val version = "2.1.6"
        val koin = "org.koin:koin-android:$version"
        val koinCore = "org.koin:koin-core:$version" // For Kotlin-Java
        val koinAndroidScope = "org.koin:koin-androidx-scope:$version"
        val koinViewModel = "org.koin:koin-androidx-viewmodel:$version"

        object Test {
            val koin = "org.koin:koin-test:$version"
        }

    }

    object HttpClient {

        object Retrofit {
            // releases (https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit)
            private const val version = "2.9.0"
            const val self = "com.squareup.retrofit2:retrofit:$version"
            const val converter = "com.squareup.retrofit2:converter-gson:$version"
        }

        object OkHttp3 {
            // releases (https://search.maven.org/search?q=g:com.squareup.okhttp3%20AND%20a:logging-interceptor)
            const val logging = "com.squareup.okhttp3:logging-interceptor:4.8.1"
            // releases (https://mvnrepository.com/artifact/com.squareup.okhttp3/mockwebserver)
            const val mock = "com.squareup.okhttp3:mockwebserver:4.8.1"
        }

    }

    object Glide {
        private const val version = "4.11.0"
        const val self = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
        const val transformations = "jp.wasabeef:glide-transformations:4.1.0"
    }

    object Test {

        object Robolectric {
            // releases (https://github.com/robolectric/robolectric/releases)
            const val self = "org.robolectric:robolectric:4.4"
        }

    }

}