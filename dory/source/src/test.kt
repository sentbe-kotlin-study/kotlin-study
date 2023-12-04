
// MemberReferences/MemberReferencesEx1.kt
package memberReferencesExercise1
import memberReferencesExercise1.Habitat.*

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
        this.filter(LAND::livesIn)

fun List<Pet>.liveInWater(): List<Pet> =
        this.filter { it.habitat == WATER }

fun List<Pet>.areAmphibious(): List<Pet> =
        this.filter(AMPHIBIOUS::livesIn)

fun List<Pet>.partitionAmphibious(): Pair<List<Pet>, List<Pet>> =
        this.partition { it.habitat == AMPHIBIOUS }

fun main() {
    val pets = listOf(
            Pet("Dog", LAND),
            Pet("Cat", LAND),
            Pet("Goldfish", WATER),
            Pet("Turtle", AMPHIBIOUS),
            Pet("Frog", AMPHIBIOUS)
    )
    println(pets.liveOnLand().toString() == "[Dog, Cat]") // 육지동물
    println(pets.liveInWater().toString() == "[Goldfish]")  // 수중동물
    pets.areAmphibious().toString() == "[Turtle, Frog]"    // 양서류
    println(pets.partitionAmphibious().toString() ==               // 양서류와 양서류 아닌 것
            "([Turtle, Frog], [Dog, Cat, Goldfish])")
}