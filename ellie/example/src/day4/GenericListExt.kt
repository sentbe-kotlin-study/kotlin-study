package day4

val <T> List<T>.firstOrNull: T?
    get() = if (isEmpty()) null else this[0]

fun main() {
    println(listOf(1,2,3).firstOrNull) // ??
    println(listOf<String>().firstOrNull) // ??
}