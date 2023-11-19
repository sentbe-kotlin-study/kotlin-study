package day4

import atomictest.eq

fun main() {
    var x: String? = "abc"
    x!! eq "abc"
    x = null
    try {
        val s: String = x!!
    } catch (e: Throwable) {
        println(e)
    }
}