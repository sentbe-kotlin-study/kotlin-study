package day3

import atomictest.eq

fun main() {
    val mutableSet = mutableSetOf<Int>()

    try {
        mutableSet += 4
        mutableSet += 3
        mutableSet += 3

        mutableSet -= 2

        mutableSet += 7

        println(mutableSet) // [1]
    } catch (e: Throwable) {
        println(e)
    }

    println(mutableSet.toList()) // [2]
}