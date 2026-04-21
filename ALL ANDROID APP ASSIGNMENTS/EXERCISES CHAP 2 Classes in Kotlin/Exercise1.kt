// Exercise 1: Model a Zoo

// Classe abstraite Animal
abstract class Animal(
    val name: String,
    open val legs: Int
) {
    // Méthode abstraite
    abstract fun makeSound(): String
}


// Classe Dog qui hérite de Animal
class Dog(name: String) : Animal(name, 4) {

    override fun makeSound(): String {
        return "Woof!"
    }
}


// Classe Cat qui hérite de Animal
class Cat(name: String) : Animal(name, 4) {

    override fun makeSound(): String {
        return "Meow!"
    }
}

// Fonction principale
fun main() {

    // Liste d'animaux
    val animals: List<Animal> = listOf(
        Dog("Buddy"),
        Cat("Whiskers")
    )

    // Parcourir la liste et afficher les sons
    for (animal in animals) {
        println("${animal.name} says ${animal.makeSound()}")
    }
}