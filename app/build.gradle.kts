
plugins {
    id(Plugins.Android.self)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
    id(Plugins.Android.Navigation.self)
    id(Plugins.Firebase.crashlytics)
    id(Plugins.Firebase.perf)
    id(Plugins.Firebase.appDistribution)
}

android {

    compileSdkVersion(AndroidConfig.compileSdk)
    buildToolsVersion(AndroidConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdk)
        targetSdkVersion(AndroidConfig.targetSdk)

        applicationId = AndroidConfig.applicationId
        versionCode = Version.code
        versionName = Version.name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /*vectorDrawables.apply {
            useSupportLibrary = true
            generatedDensities(*(AndroidConfig.noGeneratedDensities))
        }*/

        /*signingConfigs {
            create("release") {
                storeFile = rootProject.file("signing/dotanuki-demos.jks")
                storePassword = "dotanuki"
                keyAlias = "dotanuki-alias"
                keyPassword = "dotanuki"
            }
        }*/

        buildTypes {

            getByName("debug") {
                applicationIdSuffix = ".debug"
                versionNameSuffix = "-DEBUG"
                isTestCoverageEnabled = true
            }

            getByName("release") {
                isMinifyEnabled = true
                isShrinkResources = true

//                val proguardConfig = ProguardConfig("$rootDir/proguard")
//                proguardFiles(*(proguardConfig.customRules))
//                proguardFiles(getDefaultProguardFile(proguardConfig.androidRules))
//
//                signingConfig = signingConfigs.findByName("release")
            }

        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = KotlinConfig.targetJVM
        }

        /*testOptions {
            unitTests.isReturnDefaultValues = true
            unitTests.isIncludeAndroidResources = true
        }*/

        buildFeatures.viewBinding = true

    }
    dynamicFeatures = mutableSetOf(":features:home", ":features:contentdetail")
}

dependencies {
    implementation(project(":infrastructure:data"))
    implementation(project(":infrastructure:domain"))
    implementation(project(":infrastructure:common"))
    implementation(project(":infrastructure:common-ui"))

    implementation(Libraries.AndroidX.Lifecycle.runtime)
    implementation(Libraries.AndroidX.Lifecycle.common)

    implementation(Libraries.AndroidX.Test.core)
    implementation(Libraries.AndroidX.Test.junit)
    implementation(Libraries.AndroidX.Test.espresso)

    implementation(Libraries.DI.koin)

    implementation(Libraries.Test.Robolectric.self)

}

/*

dependencies {

    implementation project(":domain")
    implementation project(":core")
    implementation project(":data")

    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

    // Kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    // Androidx
    implementation "androidx.core:core-ktx:$androidx_corektx_version"
    implementation "androidx.constraintlayout:constraintlayout:$androidx_constraintlayout_version"
    implementation "androidx.constraintlayout:constraintlayout-solver:$androidx_constraintlayout_version"
    implementation "androidx.recyclerview:recyclerview:$androidx_recyclerview_version"
    implementation "androidx.cardview:cardview:$androidx_cardview_version"
    implementation "androidx.appcompat:appcompat:$androidx_appcompat_version"
    implementation "androidx.navigation:navigation-fragment-ktx:$androidx_navigation"
    implementation "androidx.navigation:navigation-ui-ktx:$androidx_navigation"

    // Material Components
    implementation "com.google.android.material:material:$materialcomponents_version"

    // Lifecycle
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

    // Stetho
    implementation "com.facebook.stetho:stetho:$stetho_version"
    implementation "com.facebook.stetho:stetho-okhttp3:$stetho_okhttpp3_version", {
        exclude group: 'com.google.code.findbugs', module: 'jsr305'
    }

    // Glide
    implementation "com.github.bumptech.glide:glide:$glide_version"
    kapt "com.github.bumptech.glide:compiler:$glide_version"

    // Glide Transformations
    implementation "jp.wasabeef:glide-transformations:$glide_transformations_version"

    // Firebase
    implementation 'com.google.firebase:firebase-analytics:17.5.0'
    implementation 'com.google.firebase:firebase-crashlytics:17.2.1'
    implementation 'com.google.firebase:firebase-perf:19.0.8'

    testImplementation 'junit:junit:4.13'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    apply plugin: 'com.google.gms.google-services'

}*/
