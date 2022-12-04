package y22.day04

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val pairs = getPairs(input)
        return findOverlappingPairs(pairs, false)
    }

    fun part2(input: List<String>): Int {
        val pairs = getPairs(input)
        return findOverlappingPairs(pairs, true)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day04/sample")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("y22/day04/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun findOverlappingPairs(assignments: List<Pair<Int, Int>>, isPartial: Boolean = false): Int {
    var overlappingPairsCount = 0

    // Iterate through the sorted assignments and compare each assignment to the next one in the list
    for (i in 0 until assignments.size - 1 step 2) {
        val first = assignments[i]
        val second = assignments[i + 1]

        // Check if the next assignment fully contains the current one, or if the current assignment fully contains the next one
        if (!isPartial) {
            if (second.first >= first.first && second.second <= first.second) {
                overlappingPairsCount++
            } else if (first.first >= second.first && first.second <= second.second) {
                overlappingPairsCount++
            }
        }
        if (isPartial) {
            val firstRange = first.first..first.second
            val secondRange = second.first..second.second
            if (firstRange.any { secondRange.contains(it) }) {
                overlappingPairsCount++
            }
        }
    }

    return overlappingPairsCount
}

fun getPairs(input: List<String>): List<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    input.forEach { row ->
        val pairRow = row.split(",")
        val pairL = pairRow[0].split("-")
        val pairR = pairRow[1].split("-")
        pairs.add(Pair(pairL[0].toInt(), pairL[1].toInt()))
        pairs.add(Pair(pairR[0].toInt(), pairR[1].toInt()))
    }
    return pairs
}
