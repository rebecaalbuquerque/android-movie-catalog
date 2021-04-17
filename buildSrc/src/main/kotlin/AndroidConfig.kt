import java.util.Collections.emptySet

object AndroidConfig {

    const val applicationId = "com.albuquerque.moviecatalog"

    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = compileSdk

    const val buildToolsVersion = "30.0.1"

    const val instrumentationTestRunner = "androidx.test.runner.AndroidJUnitRunner"

    val noGeneratedDensities = emptySet<String>().toTypedArray()
}
