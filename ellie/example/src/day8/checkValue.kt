package day8
fun checkValue(value: Int) {
  try {
    println(value) // [1]
    if (value <= 0)
      throw IllegalArgumentException(
        "value must be positive: $value") 
  } finally {
    println("In finally clause for $value") // [2]
  }
}

fun main() {
  listOf(10, -10).forEach {
    try {
      checkValue(it)
    } catch (e: IllegalArgumentException) {
      println("In catch clause for main()") // [3]
      println(e.message) // [4]
    }
  }
}