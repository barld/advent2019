import java.io.File
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    println("day 1A: " + day1A())
    println("day 1B: " + day1B())

    println("day 2A: " + day2A())
    println("day 2B: " + day2B())

    println("day 3A: " + day3A())
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

fun day2B(): Int {
    val listState = File("C:\\Users\\Barld Boot\\IdeaProjects\\advent2019\\src\\day2.txt")
        .readText()
        .split(',')
        .map { it.toInt() }

    var state = listState.toIntArray()

    for(noun in 0..99){
        for(verb in 0..99){
            state[1] = noun
            state[2] = verb

            var i = 0
            while(state[i] != 99){
                state[state[i+3]] = when {
                    state[i] == 1 -> state[state[i+1]] + state[state[i+2]]
                    else -> state[state[i+1]] * state[state[i+2]]
                }
                i += 4
            }
            if(state[0] == 19690720) return 100 * noun + verb
            state = listState.toIntArray()
        }
    }



    return 0
}

fun day3A(): Int {
    val input = File("C:\\Users\\Barld Boot\\IdeaProjects\\advent2019\\src\\day3.txt")
        .readLines()
        .map { it.split(',') }

    fun takeStep(origin: Pair<Int, Int>, direction: Char): Pair<Int, Int> {
        return when (direction) {
            'L' -> Pair(origin.first - 1, origin.second)
            'R' -> Pair(origin.first + 1, origin.second)
            'U' -> Pair(origin.first, origin.second + 1)
            else -> Pair(origin.first, origin.second - 1)
        }
    }

    fun manhatan(coordinate: Pair<Int,Int>): Int {
        return coordinate.first.absoluteValue + coordinate.second.absoluteValue
    }

    val coordinates = input.map {
        it.flatMap<String, Char> { s ->
            val to = s.drop(1).toInt()
            (1..to).map { _ -> s[0] }
        }
            .fold(emptyList<Pair<Int, Int>>().plus(Pair(0, 0)), { acc, item -> acc.plus(takeStep(acc.last(), item)) })
    }

    return coordinates[0].intersect(coordinates[1])
        .map{ manhatan(it) }
        .filter { it > 0 }
        .min()!!
}

