package y23.dayxx

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y23/dayxx/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 1)
    check(r2 == 1)

    val input = readInput("y23/dayxx/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
