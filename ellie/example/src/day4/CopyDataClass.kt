package day4

data class Person(
    val name:String,
    val age:Int
)

fun main() {
    val Ellie = Person("Ellie", 30)
    val Irine = Ellie.copy(name = "Irine")
    val EllieCopy = Ellie.copy()

    println(Ellie.equals(Irine))
    println(Ellie.equals(EllieCopy))
}