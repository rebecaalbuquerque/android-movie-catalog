// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }

    dependencies {
        classpath(Plugins.Google.Services.Classpath.gms)
        classpath(Plugins.Firebase.Classpath.appDistribution)
        classpath(Plugins.Firebase.Classpath.crashlytics)
        classpath(Plugins.Firebase.Classpath.perf)
        classpath(Plugins.JUnit.Classpath.jUnitPlatform)
        classpath(Plugins.JUnit.Classpath.jUnit5)
    }

}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
