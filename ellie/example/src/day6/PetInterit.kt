package day6
import atomictest.eq

interface Pet {
    abstract fun speak()
}

class Dog: Pet {
    override fun speak()  {
        println("Bark")
    }
}

class Cat: Pet {
    override fun speak()  {
        println("Meow")
    }
}

class Hamster: Pet {
    override fun speak()  {
        println("Squeak")
    }
}

fun main() {
    val pets :List<Pet> = listOf(Dog(), Cat(), Hamster());
    pets.map {it.speak()}
}