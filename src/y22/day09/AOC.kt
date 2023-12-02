package y22.day09

import printResult
import readInput
import java.lang.Math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return solve(input, knotIndex = 1)
    }

    fun part2(input: List<String>): Int {
        return solve(input, knotIndex = 9)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day09/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 13)
    check(r2 == 1)

    val input = readInput("y22/day09/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}

fun getNewKnotPos(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> {
    val (hX, hY) = head
    val (tX, tY) = tail
    val dX = abs(hX - tX)
    val dY = abs(hY - tY)
    return when {
        dX == 2 && dY == 2 -> (if (tX < hX) hX - 1 else hX + 1) to (if (tY < hY) hY - 1 else hY + 1)
        dX == 2 -> (if (tX < hX) hX - 1 else hX + 1) to hY
        dY == 2 -> hX to (if (tY < hY) hY - 1 else hY + 1)
        else -> tX to tY
    }
}

fun solve(input: List<String>, knotIndex: Int): Int {
    val hor = mapOf("L" to -1, "R" to 1)
    val ver = mapOf("U" to -1, "D" to 1)
    val knots = MutableList(10) { 0 to 0 }
    val points = mutableSetOf(0 to 0)
    input.forEach { line ->
        val (dir, steps) = line.split(" ")
        repeat(steps.toInt()) {
            knots[0] = (knots[0].first + (hor[dir] ?: 0)) to (knots[0].second + (ver[dir] ?: 0))
            repeat(9) {
                knots[it + 1] = getNewKnotPos(knots[it], knots[it + 1])
            }
            points += knots[knotIndex]
        }
    }
    return points.size
}