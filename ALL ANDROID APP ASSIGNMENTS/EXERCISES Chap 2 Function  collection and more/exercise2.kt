//EXERCISE 2: Transforming between collection types
fun main() {

    val words = listOf("Apple", "Cat", "Banana", "Dog", "Elephant", "Car")

    // Créer une map : mot -> longueur
    val wordLengths = words.associateWith { it.length }

    println("Map complète:")
    println(wordLengths)

    println("\nMots avec longueur > 4:")

    // Filtrer les entrées avec longueur > 4
    wordLengths
        .filter { it.value > 4 }
        .forEach { (word, length) ->
            println("$word -> $length")
        }
}