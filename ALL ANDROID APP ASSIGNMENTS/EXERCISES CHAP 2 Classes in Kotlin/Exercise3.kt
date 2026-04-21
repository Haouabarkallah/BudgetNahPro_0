// Exercise 3 : Drawable Shapes with interfaces

// Interface Drawable
interface Drawable {
    fun draw()
}

// Classe Circle qui implémente Drawable
class Circle(val radius: Int) : Drawable {

    override fun draw() {
        println("Circle with radius $radius")

        println("  ***  ")
        println(" *   * ")
        println("  ***  ")
    }
}

// Classe Square qui implémente Drawable
class Square(val side: Int) : Drawable {

    override fun draw() {
        println("Square with side length $side")

        println("*********")
        println("*       *")
        println("*       *")
        println("*********")
    }
}

// Fonction principale
fun main() {

    val shapes: List<Drawable> = listOf(
        Circle(3),
        Square(4)
    )

    for (shape in shapes) {
        shape.draw()
        println()
    }
}