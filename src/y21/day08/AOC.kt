package y21.day08

import readInput

private fun String.isDesiredDigit() = count() in listOf(1, 2, 3, 4, 7)

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.split(" | ") }
            .map { (_, output) -> output.split(" ") }
            .flatten()
            .fold(0) { amount, digit -> if (digit.isDesiredDigit()) amount + 1 else amount }
    }

    fun part2(input: List<String>): Int {
        val n = listOf(42, 17, 34, 39, 30, 37, 41, 25, 49, 45)
        var total = 0
        input.forEach { line ->
            val parts = line.split(" | ")
            var output = parts[1]

            for (c in "abcdefg".toCharArray()) {
                output = output.replace(
                    c.toString() + "", parts[0].chars()
                        .filter { cc: Int -> cc == c.code }.count().toString()
                )
            }

            total += output.split(" ")
                .map { d ->
                    d.chars().map(Character::getNumericValue)
                        .reduce(0, Integer::sum)
                }
                .map { n.indexOf(it) }
                .joinToString("") { it.toString() }
                .toInt()

        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y21/day08/sample")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("y21/day08/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
