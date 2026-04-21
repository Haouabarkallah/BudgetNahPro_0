
// Interface pour le calcul de note

interface Gradable {
    fun calculateGrade(): String
}

// Classe abstraite Person

abstract class Person(
    open val name: String
) {
    abstract fun describe(): String
}

// Data class Subject

data class Subject(
    val name: String,
    val score: Double?
)

// Classe Semester

class Semester(
    val title: String,
    val subjects: List<Subject>
) {

    fun calculateGPA(): Double {

        val validScores = subjects.mapNotNull { it.score }

        if (validScores.isEmpty()) return 0.0

        val grades = validScores.map {

            when {
                it >= 90 -> 4.0
                it >= 80 -> 3.0
                it >= 70 -> 2.0
                it >= 60 -> 1.0
                else -> 0.0
            }
        }

        return grades.average()
    }
}

// Data class Student

data class Student(
    override val name: String,
    val score: Double?,
    val semester: Semester? = null
) : Person(name), Gradable {

    override fun calculateGrade(): String {

        val s = score ?: return "No Score"

        return when {
            s >= 90 -> "A"
            s >= 80 -> "B"
            s >= 70 -> "C"
            s >= 60 -> "D"
            else -> "F"
        }
    }

    override fun describe(): String {

        val baseInfo = if (score == null) {
            "No score for $name"
        } else {
            "$name scored $score : Grade ${calculateGrade()}"
        }

        // Scope function
        return semester?.let {
            val gpa = "%.2f".format(it.calculateGPA())
            "$baseInfo | Semester: ${it.title} | GPA: $gpa"
        } ?: baseInfo
    }
}

// Classe Teacher

class Teacher(
    override val name: String,
    val subject: String
) : Person(name) {

    override fun describe(): String {
        return "Teacher $name teaches $subject"
    }

    override fun toString(): String {
        return "Teacher(name=$name, subject=$subject)"
    }
}

// Programme principal

fun main() {

    val semester1 = Semester(
        "Semester 1",
        listOf(
            Subject("Math", 92.0),
            Subject("Physics", 85.0),
            Subject("Programming", 88.0)
        )
    )

    val semester2 = Semester(
        "Semester 2",
        listOf(
            Subject("Math", 74.0),
            Subject("Physics", 69.0),
            Subject("Programming", 80.0)
        )
    )


    // Polymorphisme
    val people: List<Person> = listOf(
        Student("Amira", 92.0, semester1),
        Student("Bovan", 74.0, semester2),
        Student("Clavia", null, null),
        Teacher("Mr.Mbarga", "Mathematics")
    )

    println("School Members ")

    for (person in people) {
        println(person.describe())
    }
}