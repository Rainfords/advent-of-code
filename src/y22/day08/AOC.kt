package y22.day08

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.indices.sumOf { i ->
            input[0].indices.count { j ->
                val left = (j - 1 downTo 0).map { i to it }
                val right = (j + 1 until input[0].length).map { i to it }
                val up = (i - 1 downTo 0).map { it to j }
                val down = (i + 1 until input.size).map { it to j }
                listOf(left, right, up, down).any { trees ->
                    trees.all { (x, y) -> input[x][y] < input[i][j] }
                }
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.indices.maxOf { i ->
            input[0].indices.maxOf { j ->
                val left = (j - 1 downTo 0).map { i to it }
                val right = (j + 1 until input[0].length).map { i to it }
                val up = (i - 1 downTo 0).map { it to j }
                val down = (i + 1 until input.size).map { it to j }
                listOf(left, right, up, down).map { trees ->
                    minOf(trees.takeWhile { (x, y) -> input[x][y] < input[i][j] }.size + 1, trees.size)
                }.reduce(Int::times)
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day08/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 21)
    check(r2 == 8)

    val input = readInput("y22/day08/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
