plugins { id("library") }

dependencies {

    implementation(project(":infrastructure:domain"))
    implementation(project(":infrastructure:common"))

    implementation(Libraries.HttpClient.Retrofit.self)
    implementation(Libraries.HttpClient.Retrofit.converter)
    implementation(Libraries.HttpClient.OkHttp3.logging)

    testImplementation(Libraries.HttpClient.OkHttp3.mock)

}