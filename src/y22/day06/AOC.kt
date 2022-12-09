package y22.day06

import printResult
import readAsText

fun main() {
    fun calc(input: String, size: Int): Int {
        val letters = input.chunked(1)
        for (pos in letters.indices) {
            val chunk = mutableSetOf<String>()
            for (i in 0..size - 1) {
                chunk.add(letters[pos + i])
            }
            if (chunk.size == size) return pos + size
        }
        return -1
    }

    fun part1(input: String): Int {
        return calc(input, 4)
    }

    fun part2(input: String): Int {
        return calc(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readAsText("y22/day06/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("Sample #1 Was") { r1 }
    printResult("Sample #2 Was") { r2 }
    check(r1 == 7)
    check(r2 == 19)

    val input = readAsText("y22/day06/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
