package day4

enum class Direction (val notation: String) {
    North("N"), South("S"), East("E"), West("W");
    val opposite : Direction
        get() = when(this) {
            North -> South
            South -> North
            West -> East
            East -> West
        }
}

fun main() {
    println(Direction.North.notation)
    println(Direction.North.opposite)
    println(Direction.West.opposite.opposite)
    println(Direction.North.opposite.notation)
}