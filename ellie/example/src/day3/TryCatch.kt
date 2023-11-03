package day3

fun main() {
    try {
        var a = 1 / 0
    } catch(e: Throwable) {
        println(e.toString())
    }
}