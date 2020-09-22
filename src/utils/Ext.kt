package utils

fun String.toSlug(): String {
    val regex = Regex("[^A-Za-z0-9 ]")
    val slug: String = this.let { regex.replace(it, "") }
    return slug.replace(" ", "-").toLowerCase()
}