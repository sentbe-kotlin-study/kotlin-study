package day5
// Lambdas/Task1.kt

fun transform(list: List<String>): List<Int> = list.map { it.length }

fun main() {
    // 여기가 모두 true 가 되도록
    println(transform(listOf("abc", "ab")))
    println(transform(listOf("", "abdef", "x")))
    println(transform(listOf("123456789")))
}