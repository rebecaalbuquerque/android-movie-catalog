plugins { id("library") }

dependencies {

    api(Libraries.Kotlin.Coroutines.core)
    api(Libraries.Kotlin.Coroutines.android)

    api(Libraries.AndroidX.View.core)

    api(Libraries.DI.koin)
    api(Libraries.DI.koinCore)
    api(Libraries.DI.koinAndroidScope)
    api(Libraries.DI.koinViewModel)

    api(Libraries.Google.Gson.self)

    api(Libraries.Google.Firebase.analytics)
    api(Libraries.Google.Firebase.analyticsKtx)

    testImplementation(Libraries.DI.Test.koin)
    testImplementation(Libraries.DI.koinCore)
    testImplementation(Libraries.AndroidX.Test.core)
    testImplementation(Libraries.AndroidX.Test.junit)
    testImplementation(Libraries.AndroidX.Test.espresso)
    testImplementation(Libraries.Test.Robolectric.self)

}