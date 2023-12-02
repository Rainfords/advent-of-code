package y22.day11

import printResult
import readInput

fun main() {
    fun solve(input: List<String>, rounds: Int, divideBy: Int): Long {
        val monkeys = input.chunked(7)
        val inspectionCounts = MutableList(monkeys.size) { 0L }
        val items = monkeys.map { lines ->
            lines[1].substringAfter(": ").split(", ").map { it.toLong() }.toMutableList()
        }
        val mod = monkeys.map { it[3].split(" ").last().toInt() }.reduce(Int::times)
        repeat(rounds) {
            monkeys.forEachIndexed { m, lines ->
                items[m].forEach { old ->
                    inspectionCounts[m]++
                    val (d, m1, m2) = (3..5).map { lines[it].split(" ").last().toInt() }
                    val x = lines[2].split(" ").last().toLongOrNull() ?: old
                    var worry = if ("+" in lines[2]) old + x else old * x
                    worry = if (divideBy == 1) worry % mod else worry / divideBy
                    items[if (worry % d == 0L) m1 else m2] += worry
                }
                items[m].clear()
            }
        }
        return inspectionCounts.sortedDescending().let { it[0] * it[1] }
    }

    fun part1(input: List<String>): Long {
        return solve(input, rounds = 20, divideBy = 3)
    }

    fun part2(input: List<String>): Long {
        return solve(input, rounds = 10000, divideBy = 1)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day11/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 10605L)
    check(r2 == 2713310158L)

    val input = readInput("y22/day11/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
