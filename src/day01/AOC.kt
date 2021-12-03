package day01

import readInputAsInts

fun main() {
    fun part1(input: List<Int>): Int {
        return input.windowed(2).count { (a, b) -> a < b }
    }

    fun part2(input: List<Int>): Int {
        return input
            .windowed(3)
            .windowed(2)
            .count { (a, b) ->
                a.sum() < b.sum()
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInts("day01/sample")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputAsInts("day01/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
