package day11

import readInput

typealias DumboOctopusies = List<List<DumboOctopus>>

data class DumboOctopus(
    var energy: Int,
    var flashed: Boolean = false,
    var neighbors: List<DumboOctopus> = emptyList()
) {
    fun flash(): Int {
        if (flashed) return 0 else energy += 1
        if (energy > 9) {
            energy = 0
            flashed = true
            return 1 + neighbors.sumOf { it.flash() }
        }
        return 0
    }
}

fun findNeighbors(octopus: DumboOctopusies) =
    octopus.mapIndexed { y, length ->
        length.mapIndexed { x, dumboOctopus ->
            dumboOctopus.neighbors = getAdjacent(x, y, octopus)
            dumboOctopus
        }
    }

fun getAdjacent(x: Int, y: Int, octopusies: DumboOctopusies): List<DumboOctopus> {
    val length = octopusies.first().size
    val depth = octopusies.size
    val right = if (x < length - 1) octopusies[y][x + 1] else null
    val left = if (x > 0) octopusies[y][x - 1] else null
    val above = if (y > 0) octopusies[y - 1][x] else null
    val aboveAndRight = if (y > 0 && x < length - 1) octopusies[y - 1][x + 1] else null
    val aboveAndLeft = if (y > 0 && x > 0) octopusies[y - 1][x - 1] else null
    val below = if (y < depth - 1) octopusies[y + 1][x] else null
    val belowAndRight = if (y < depth - 1 && x < length - 1) octopusies[y + 1][x + 1] else null
    val belowAndLeft = if (y < depth - 1 && x > 0) octopusies[y + 1][x - 1] else null
    return listOfNotNull(right, left, above, aboveAndRight, aboveAndLeft, below, belowAndRight, belowAndLeft)
}

fun main() {
    fun part1(input: List<String>): Int {
        val loadsOfMonsters = input.map {
            it.map { energy -> DumboOctopus(energy.digitToInt()) }
        }.let { findNeighbors(it) }.flatten()

        val sum = (1..100).sumOf { _ ->
            loadsOfMonsters.forEach { it.flashed = false }
            loadsOfMonsters.sumOf { it.flash() }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val loadsOfMonsters = input.map {
            it.map { energy -> DumboOctopus(energy.digitToInt()) }
        }.let { findNeighbors(it) }.flatten()

        (1..1000).forEach { day ->
            loadsOfMonsters.forEach { it.flashed = false }
            if (loadsOfMonsters.sumOf { it.flash() } == 100) return day
        }
        throw RuntimeException()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day11/sample")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("day11/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
