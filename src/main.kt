import java.io.File

fun main(args: Array<String>) {
    println("day 1A: " + day1A())
    println("day 1B: " + day1B())
}

fun day1A(): Int {
    return File("C:\\Users\\Barld Boot\\IdeaProjects\\advent2019\\src\\input1a.txt")
        .readLines()
        .map { (it.toInt() / 3) - 2 }
        .sum()
}

fun day1B(): Int {
    fun calculateExtraFuel(mass: Int): Int {
        val n = (mass/3)-2
        return when {
             n <= 0 -> 0
            else -> n + calculateExtraFuel(n)
        }
    }

    return File("C:\\Users\\Barld Boot\\IdeaProjects\\advent2019\\src\\input1a.txt")
        .readLines()
        .map { (it.toInt() / 3) - 2 }
        .map {it + calculateExtraFuel(it)}
        .sum()
}

