import java.io.File

fun main(args: Array<String>) {
    println("day 1A: " + day1A())
    println("day 1B: " + day1B())

    println("day 2A: " + day2A())
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


fun day2A(): Int {
    val state = File("C:\\Users\\Barld Boot\\IdeaProjects\\advent2019\\src\\day2.txt")
        .readText()
        .split(',')
        .map { it.toInt() }
        .toIntArray()

    state[1] = 12
    state[2] = 2

    var i = 0
    while(state[i] != 99){
        state[state[i+3]] = when {
            state[i] == 1 -> state[state[i+1]] + state[state[i+2]]
            else -> state[state[i+1]] * state[state[i+2]]
        }
        i += 4
    }

    return state[0]
}
