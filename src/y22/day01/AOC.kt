package y22.day01

import readAsText

fun main() {
    fun part1(input: String): Long {
        return input
            .split("\n\n")
            .map {
                it.lines()
                    .map(String::toLong)
                    .sum()
            }
            .maxOf { it }
    }

    fun part2(input: String): Long {
        return input
            .split("\n\n")
            .map {
                it.lines()
                    .map(String::toLong)
                    .sum()
            }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readAsText("y22/day01/sample")
    val p1Result = part1(testInput)
    val p2Result = part2(testInput)

    check(p1Result == 24000L)
    check(p2Result == 45000L)

    val input = readAsText("y22/day01/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
