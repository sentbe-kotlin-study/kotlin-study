package chapter2

class Scientist(val name: String) {
    override fun toString() : String {
        return "Scientist('$name')"
    }
}

fun main() {
    val ellie = Scientist("Ellie")
    println(ellie)
}