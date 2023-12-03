package y23.day02

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEachIndexed { idx, str ->
            val list = str.split(*arrayOf(";", ":", ",")).drop(1).map { it.trim().split(" ") }
            for (pair in list) {
                when (pair.last()) {
                    "red" -> if (pair.first().toInt() > 12) return@forEachIndexed
                    "green" -> if (pair.first().toInt() > 13) return@forEachIndexed
                    "blue" -> if (pair.first().toInt() > 14) return@forEachIndexed
                }
            }
            sum += idx + 1
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (str in input) {
            val list = str.split(*arrayOf(";", ":", ",")).drop(1).map { it.trim().split(" ") }
            var red = 0
            var green = 0
            var blue = 0
            for (map in list) {
                when (map[1]) {
                    "red" -> {
                        red = listOf(red, map[0].toInt()).maxOrNull() ?: 0
                    }

                    "green" -> {
                        green = listOf(green, map[0].toInt()).maxOrNull() ?: 0
                    }

                    "blue" -> {
                        blue = listOf(blue, map[0].toInt()).maxOrNull() ?: 0
                    }
                }
            }
            sum += red * green * blue
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y23/day02/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 8)
    check(r2 == 2286)

    val input = readInput("y23/day02/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
