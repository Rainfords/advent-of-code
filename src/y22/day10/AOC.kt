package y22.day10

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val arr = mutableListOf(0, 1)
        input.forEach {
            val tokens = it.split(" ")
            arr += arr.last()
            if (tokens[0] == "addx")
                arr += arr.last() + tokens[1].toInt()
        }
        return (20..220 step 40).sumOf { it * arr[it] }
    }

    fun part2(input: List<String>) {
        var x = 1
        var i = 0
        val pixels = mutableListOf<Char>()
        input.forEach {
            val tokens = it.split(" ")
            pixels += if (i++ % 40 in (x - 1)..(x + 1)) '#' else '.'
            if (tokens[0] == "addx") {
                pixels += if (i++ % 40 in (x - 1)..(x + 1)) '#' else '.'
                x += tokens[1].toInt()
            }
        }
        pixels.chunked(40).forEach { row ->
            println(row.joinToString(" "))
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day10/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    //printResult("#2 is: ") { r2 }
    check(r1 == 13140)
    //check(r2 == 1)

    val input = readInput("y22/day10/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
