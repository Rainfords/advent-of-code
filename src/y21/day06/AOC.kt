package y21.day06

import printResult
import readInputAsInts
import y21.day06.Day06.part1
import y21.day06.Day06.part2

const val MAX_DAY = 8
const val REPRODUCTION_INTERVAL = 6

object Day06 {
    fun part1(input: List<Int>): Long {
        return simulateCycles(input, 80)
    }

    fun part2(input: List<Int>): Long {
        return simulateCycles(input, 256)
    }


}

fun main() {
    val testInput = readInputAsInts("y21/day06/sample", ",")
    with(part1(testInput)) { check("5934".toLong() == this) { "result test 1: $this" } }
    with(part2(testInput)) { check("26984457539".toLong() == this) { "result test 2: $this" } }

    val input = readInputAsInts("y21/day06/input", ",")
    printResult("Part 1") { part1(input) }
    printResult("Part 2") { part2(input) }
}

fun simulateCycles(input: List<Int>, cycles: Int): Long {
    val timerCounts = LongArray(MAX_DAY + 1) { i -> input.count { it == i }.toLong() }

    repeat(cycles) {
        val newFishCount = timerCounts[0]
        for (i in 0 until MAX_DAY) {
            timerCounts[i] = timerCounts[i + 1]
        }
        timerCounts[REPRODUCTION_INTERVAL] += newFishCount
        timerCounts[MAX_DAY] = newFishCount
    }

    return timerCounts.sum()
}