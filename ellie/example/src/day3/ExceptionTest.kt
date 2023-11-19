package day3


// Exceptions/Task3.kt
import atomictest.capture
import atomictest.eq

fun repeatChar(ch: Char, n: Int): String {
    var result = ""
    repeat(n) {
        result += ch
    }
    return result
}

fun main() {
    repeatChar('a', 4) eq "aaaa"
    capture {
        repeatChar('c', -2)
    } eq "IllegalArgumentException: Count 'n' must be non-negative, but was -2."
}