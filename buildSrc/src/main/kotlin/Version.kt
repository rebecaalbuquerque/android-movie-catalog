object Version {
    private const val major = 0
    private const val minor = 0
    private const val patch = 4
    private const val number = 6

    val name by lazy {
        "$major.$minor.$patch"
    }

    val code by lazy {
        number
    }
}