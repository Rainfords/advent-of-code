package y22.day15

import printResult
import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>, y: Int): Int {
        val beacons = mutableSetOf<Pair<Int, Int>>()
        val possibleX = mutableSetOf<Int>()
        input.forEach { line ->
            val (sX, sY, bX, bY) = Regex("[-\\d]+").findAll(line).map { it.value.toInt() }.toList()
            val dx = abs(sY - bY) + abs(sX - bX) - abs(sY - y)
            beacons += bX to bY
            possibleX += sX - dx..sX + dx
        }
        return possibleX.count { it to y !in beacons }
    }

    fun part2(input: List<String>, maxCoord: Int): Long {
        val sensors = mutableListOf<Triple<Long, Long, Long>>()
        input.forEach { line ->
            val (sX, sY, bX, bY) = Regex("[-\\d]+").findAll(line).map { it.value.toLong() }.toList()
            val d = abs(sY - bY) + abs(sX - bX)
            sensors += Triple(sX, sY, d)
        }
        sensors.forEach { (x, y, d) ->
            // Find valid positions at distance d+1 from sensor
            for (dx in 0..(d + 1)) {
                val dy = (d + 1) - dx
                for ((dirX, dirY) in listOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1)) {
                    val (bX, bY) = x + dx * dirX to y + dy * dirY
                    if (bX !in 0..maxCoord || bY !in 0..maxCoord)
                        continue
                    if (sensors.none { (sX, sY, d) -> abs(sY - bY) + abs(sX - bX) <= d })
                        return bX * 4000000 + bY
                }
            }
        }
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day15/sample")
    val r1 = part1(testInput, 10)
    val r2 = part2(testInput, 20)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 26)
    check(r2 == 56000011L)

    val input = readInput("y22/day15/input")
    println("Part 1 PASSED: ${part1(input, 2000000)}")
    println("Part 2 PASSED: ${part2(input, 4000000)}")
}
