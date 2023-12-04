package day5
import atomictest.*
data class Pet(
    var name: String,
    val habitat: Habitat
) {
    override fun toString() = name
}

enum class Habitat {
    LAND, WATER, AMPHIBIOUS;
    fun livesIn(pet: Pet) = pet.habitat == this
}

fun List<Pet>.liveOnLand(): List<Pet> =
    this.filter {it.habitat == Habitat.LAND}

fun List<Pet>.liveInWater(): List<Pet> =
    this.filter {it.habitat == Habitat.WATER}

fun List<Pet>.areAmphibious(): List<Pet> =
    this.filter {it.habitat == Habitat.AMPHIBIOUS}

fun List<Pet>.partitionAmphibious(): Pair<List<Pet>, List<Pet>> =
    Pair(this.filter {it.habitat == Habitat.AMPHIBIOUS }, this.filter {it.habitat != Habitat.AMPHIBIOUS })

fun main() {
    val pets = listOf(
        Pet("Dog", Habitat.LAND),
        Pet("Cat", Habitat.LAND),
        Pet("Goldfish", Habitat.WATER),
        Pet("Turtle", Habitat.AMPHIBIOUS),
        Pet("Frog", Habitat.AMPHIBIOUS)
    )
    pets.liveOnLand() eq "[Dog, Cat]"           // 육지동물
    pets.liveInWater() eq "[Goldfish]"          // 수중동물
    pets.areAmphibious() eq "[Turtle, Frog]"    // 양서류
    pets.partitionAmphibious() eq               // 양서류와 양서류 아닌 것
           "([Turtle, Frog], [Dog, Cat, Goldfish])"
}