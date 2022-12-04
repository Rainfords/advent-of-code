package y22.day03

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        // Sum the priorities of the common item types in each rucksack
        return input.map { splitItems(it) }.sumOf { sumCommonPriorities(it.first, it.second) }
    }

    fun part2(input: List<String>): Int {
        var priorities = 0
        val groups = input.chunked(3)
        groups.map { group ->
            for (c in group[0]) {
                if (group[1].contains(c) && group[2].contains(c)) {
                    priorities += charToPriority(c)
                    break
                }
            }
        }
        return priorities
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day03/sample")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("y22/day03/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

// Split the items in a rucksack into two compartments
fun splitItems(items: String): Pair<String, String> {
    val n = items.length / 2
    val firstCompartment = items.substring(0, n)
    val secondCompartment = items.substring(n)
    return firstCompartment to secondCompartment
}

// Find the sum of the priorities of the common item types in two compartments
fun sumCommonPriorities(firstCompartment: String, secondCompartment: String): Int {
    // Find the common items in the two compartments
    val commonItems = firstCompartment.toSet() intersect secondCompartment.toSet()
    // Sum the priorities of the common items
    return commonItems.sumOf { charToPriority(it) }
}

// Convert a character to its priority
fun charToPriority(c: Char): Int {
    return when {
        c.isLowerCase() -> c.code - 96  // Lowercase letters have priorities 1 through 26
        else -> c.code - 38  // Uppercase letters have priorities 27 through 52
    }
}
