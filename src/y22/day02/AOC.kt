package y22.day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val win = mapOf('A' to 'Y', 'B' to 'Z', 'C' to 'X')
        return input.sumOf { line ->
            line[2].code - 'X'.code + 1 + if (win[line[0]] == line[2]) 6 else 0 + if (line[2] - line[0] == ('X' - 'A')) 3 else 0
        }
    }

    fun part2(input: List<String>): Int {
        val win = mapOf('A' to 'Y', 'B' to 'Z', 'C' to 'X')
        val draw = mapOf('A' to 'X', 'B' to 'Y', 'C' to 'Z')
        val lose = mapOf('A' to 'Z', 'B' to 'X', 'C' to 'Y')
        return input.sumOf { line ->
            if (line[2] == 'Z') win[line[0]]!!.code - 'X'.code + 1 + 6
            else if (line[2] == 'Y') draw[line[0]]!!.code - 'X'.code + 1 + 3
            else lose[line[0]]!!.code - 'X'.code + 1
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day02/sample")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("y22/day02/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
