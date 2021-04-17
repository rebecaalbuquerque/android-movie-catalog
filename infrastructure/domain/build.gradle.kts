plugins { id("library") }

dependencies {

    implementation(project(":infrastructure:common"))

    testImplementation(Libraries.HttpClient.OkHttp3.mock)
    testImplementation(Libraries.HttpClient.Retrofit.self)
    testImplementation(Libraries.HttpClient.Retrofit.converter)

}