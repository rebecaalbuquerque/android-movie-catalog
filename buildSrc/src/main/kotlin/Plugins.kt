object Plugins {

    object Android {
        const val self = "com.android.application"

        object Navigation {
            const val self = "androidx.navigation.safeargs.kotlin"
        }
    }

    object Kotlin {
        const val android = "kotlin-android"
        const val androidExtensions = "kotlin-android-extensions"
        const val kapt = "kotlin-kapt"
    }

    object Google {

        object Services {
            const val gms = "com.google.gms.google-services"

            object Classpath {
                // releases (https://maven.google.com/web/index.html?q=com.google.gms#com.google.gms:google-services)
                const val gms = "com.google.gms:google-services:4.3.3"
            }
        }

    }

    object Firebase {
        const val crashlytics = "com.google.firebase.crashlytics"
        const val appDistribution = "com.google.firebase.appdistribution"
        const val perf = "com.google.firebase.firebase-perf"

        object Classpath {
            // releases (https://maven.google.com/web/index.html?q=com.google.firebase#com.google.firebase:firebase-appdistribution-gradle)
            const val appDistribution = "com.google.firebase:firebase-appdistribution-gradle:2.0.1"
            // releases (https://maven.google.com/web/index.html?q=com.google.firebase#com.google.firebase:firebase-crashlytics-gradle)
            const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.2.1"
            const val perf = "com.google.firebase:perf-plugin:1.3.1"
        }

    }

    object JUnit {

        const val jUnit5 = "de.mannodermaus.android-junit5"

        object Classpath {
            const val jUnitPlatform = "org.junit.platform:junit-platform-gradle-plugin:1.0.1"
            const val jUnit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.6.2.0"
        }
    }

}