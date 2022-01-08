package day01

import readInputAsInts
import kotlin.math.abs


fun main() {
    fun part1(input: List<Int>): Int =
        (input.minOrNull()!!..input.maxOrNull()!!).minOf { pos ->
            input.sumOf { abs(it - pos) }
        }


    fun part2(input: List<Int>): Int =
        (input.minOrNull()!!..input.maxOrNull()!!).minOf { pos ->
            input.sumOf { (0..abs(it - pos)).sum() }
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInts("day07/sample", ",")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInputAsInts("day07/input", ",")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
