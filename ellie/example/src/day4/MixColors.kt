package day4

fun mixColor(first: String, second: String) =
    when (setOf(first, second)) {
        setOf("red", "blue") -> "purple"
        setOf("red", "yellow") -> "orange"
        setOf("blue", "yellow") -> "green"
        else -> "unknown"
    }

fun main() {
    println(mixColor("red", "blue")) // ??
    println(mixColor("blue", "purple")) // ??
}