package day4

class Color (
    val red: Int = 0,
    val green: Int = 0,
    val blue: Int = 0
    ) {
    override fun toString() = "($red, $green, $blue)"
}

fun main() {
    println(Color(red = 77).toString()) // ??
}