package chapter2
import atomictest.*

fun main() {
    val list = listOf(1,4,8)
    list[0] eq 1
    list.reversed() eq listOf(8,4,1)
    list.sorted() eq listOf(1,4,8)
    list.sum() eq 13
}