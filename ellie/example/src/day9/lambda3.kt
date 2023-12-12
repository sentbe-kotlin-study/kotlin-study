package day9

// TODO
fun List<Int>.secondHighest() : Int? {
    val sorted = this.toSortedSet().sortedDescending()

    if(sorted.size < 2) return null
    return sorted.elementAt(1)
}

fun main() {
    val numbers = listOf(3, 5, 7, 5, 2, 9, 9)
    val secondHighest = numbers.secondHighest()
    println("The second-highest number is: $secondHighest") // 7
}