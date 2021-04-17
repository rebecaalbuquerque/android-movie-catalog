plugins {
    id("library")
}

dependencies {

    implementation(project(":infrastructure:domain"))
    implementation(project(":infrastructure:common"))

    api(Libraries.AndroidX.View.constraintlayout)
    api(Libraries.AndroidX.View.recyclerView)
    api(Libraries.AndroidX.View.recyclerViewSolver)

    api(Libraries.Glide.self)
    api(Libraries.Glide.compiler)
    api(Libraries.Glide.transformations)
}