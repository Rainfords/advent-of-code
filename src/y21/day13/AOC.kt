package y21.day13

import readInputAsInts

fun main() {
    fun part1(input: List<Int>): Int {
        return 1
    }

    fun part2(input: List<Int>): Int {
        return 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInts("y21/day13/sample")
    check(part1(testInput) == 1)
    check(part2(testInput) == 1)

    val input = readInputAsInts("y21/day13/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
