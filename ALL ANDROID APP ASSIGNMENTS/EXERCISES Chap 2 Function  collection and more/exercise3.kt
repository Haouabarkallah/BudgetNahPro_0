//EXERCISE 3 : Complex data processing

data class Person(val name: String, val age: Int)

fun main() {

    val people = listOf(
        Person("Amira", 22),
        Person("Bovan", 30),
        Person("Agbor", 25),
        Person("Daoud", 40),
        Person("Brinda", 28),
        Person("Carlos", 35),
    )

    // Filtrer les noms qui commencent par A ou B
    val filteredPeople = people.filter {
        it.name.startsWith("A") || it.name.startsWith("B")
    }

    // Calculer la moyenne d'âge
    val averageAge = filteredPeople
        .map { it.age }
        .average()

    // Afficher avec 1 décimale
    println("Average age: %.1f".format(averageAge))
}