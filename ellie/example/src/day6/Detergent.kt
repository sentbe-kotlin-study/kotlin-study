package day6

import atomictest.eq

open class Cleanser {
    // TODO add property and functions
    var ops: MutableList<String> = mutableListOf()

    fun dilute() = ops.add("dilute")
    fun apply() = ops.add("apply")
    fun scrub() = ops.add("scrub")
}

open class Detergent : Cleanser()

fun main () {
    val detergent = Detergent()
    detergent.dilute()
    detergent.apply()
    detergent.scrub()
    detergent.ops eq
    listOf("dilute", "apply", "scrub")
}