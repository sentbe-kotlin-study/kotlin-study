package day3

class Counter() {
    var value: Int = 0
    private set

    var value2: Int = 0

    fun inc() = value++
}
fun main() {
    val counter = Counter()
    repeat(10) {
        counter.inc()
    }

    println(counter.value) // [1]
    // println(counter.value++) // [2]
    println(counter.value2++) // [3]
}