package day3

fun first(vararg numbers: Int) : String {
    var result = ""

    numbers.sortDescending()
    for (i in numbers) {
        result += "[$i]"
    }

    return result
}

fun second(vararg numbers: Int) = first(*numbers)
fun main() {
    println(second(7,9,32)) // ??
}