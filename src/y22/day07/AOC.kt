package y22.day07

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return getDirSizes(input).values.sumOf { if (it <= 100000) it else 0 }
    }

    fun part2(input: List<String>): Int {
        val dirSizes = getDirSizes(input)
        return dirSizes.values.filter { it >= (30000000 - (70000000 - dirSizes["/"]!!)) }.minOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day07/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 95437)
    check(r2 == 24933642)

    val input = readInput("y22/day07/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}

fun getDirSizes(input: List<String>): Map<String, Int> {
    val path = mutableListOf<String>()
    val dirSize = mutableMapOf<String, Int>()
    input.forEach { line ->
        val tokens = line.split(" ")
        if (tokens[1] == "cd") {
            if (tokens[2] == "..")
                path.removeLast()
            else
                path += tokens[2]
        }
        if (tokens[0][0].isDigit()) {
            path.indices.forEach { i ->
                val absolutePath = path.take(i + 1).joinToString("/")
                dirSize[absolutePath] = (dirSize[absolutePath] ?: 0) + tokens[0].toInt()
            }
        }
    }
    return dirSize
}
