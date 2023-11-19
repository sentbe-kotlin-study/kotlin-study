package day4

data class Tuple (
    val i : Int,
    val d: Double,
    val s: String,
    val b: Boolean,
    val l: List<Int>
)

fun main() {
    val tuple = Tuple(
        1, 3.14, "Mouse", false, listOf()
    )

    val (i, d, s, b, l) = tuple

    println(i)
    println(d)
    println(s)
    println(b)
    println(l)

    val (_,_,animal) = tuple
    println(animal)
}