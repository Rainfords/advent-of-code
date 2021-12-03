package day02

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        var horz = 0
        var depth = 0
        input.forEach {
            val match = Regex("(\\w+) (\\d+)").find(it)!!
            val (direction, value) = match.destructured
            val amount = value.toInt()
            when (direction) {
                "up" -> depth -= amount
                "down" -> depth += amount
                "forward" -> horz += amount
                else -> throw RuntimeException("Bad direction your crazy drunk driver!")
            }
        }
        return horz * depth
    }

    fun part2(input: List<String>): Int {
        var horz = 0
        var depth = 0
        var aim = 0
        input.forEach {
            val match = Regex("(\\w+) (\\d+)").find(it)!!
            val (direction, value) = match.destructured
            val amount = value.toInt()
            when (direction) {
                "up" -> aim -= amount
                "down" -> aim += amount
                "forward" -> {
                    horz += amount
                    depth += amount * aim
                }
                else -> throw RuntimeException("Bad direction your crazy drunk driver!")
            }
        }
        return horz * depth
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/sample")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day02/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")

}
