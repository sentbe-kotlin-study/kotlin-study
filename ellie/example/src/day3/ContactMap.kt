package day3
import atomictest.*

class Contact(
    val name: String,
    val phone: String
) {
    override fun toString(): String {
        return "Contact('$name', '$phone')"
    }
}

fun main() {
    val miffy = Contact("miffiy", "111")
    val cleo = Contact("cleo", "222")

    // TODO

    // contacts["111"] eq "miffy"
}