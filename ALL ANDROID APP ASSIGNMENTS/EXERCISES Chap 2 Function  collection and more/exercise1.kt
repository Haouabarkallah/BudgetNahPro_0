//EXERCISE 1: Build your own higher-order function


// Fonction qui traite une liste avec une lambda
fun processList(numbers: List<Int>, predicate: (Int) -> Boolean): List<Int> {

    val result = mutableListOf<Int>()

    for (n in numbers) {
        if (predicate(n)) {
            result.add(n)
        }
    }

    return result
}


// Fonction principale
fun main() {

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8)

    println("Liste originale: $numbers")

    // Lambda pour garder les nombres pairs
    val evenNumbers = processList(numbers) { n ->
        n % 2 == 0
    }

    println("Nombres pairs: $evenNumbers")


    // Lambda pour garder les nombres > 4
    val greaterThanFour = processList(numbers) { n ->
        n > 4
    }

    println("Nombres > 4: $greaterThanFour")
}