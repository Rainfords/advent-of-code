package y21.day12

import readInput

fun main() {
    fun isSmall(name: String) = name[0].isLowerCase()

    fun Map<String, Map.Entry<String, List<String>>>.findPaths(
        name: String,
        paths: List<String>,
        singleSmallCaveTwice: Boolean = false
    ): List<List<String>> =
        when {
            name == "start" && paths.isNotEmpty() -> emptyList()
            name == "end" -> listOf(paths + name)
            paths.contains(name) && isSmall(name) &&
                    paths.groupingBy { it }.eachCount()
                        .count { isSmall(it.key) && it.value >= 2 } == if (singleSmallCaveTwice) 1 else 0 -> emptyList()

            else -> requireNotNull(this[name]).value.flatMap { findPaths(it, paths + name, singleSmallCaveTwice) }
        }

    fun part1(data: Map<String, Map.Entry<String, List<String>>>): Int {
        return data.findPaths("start", emptyList()).count()
    }

    fun part2(data: Map<String, Map.Entry<String, List<String>>>): Int {
        return data.findPaths("start", emptyList(), true).count()
    }

    val input = readInput("y21/day12/input")
        .map { it.split("-") }
        .flatMap { listOf(it, it.reversed()) }
        .groupBy({ it[0] }, { it[1] })
        .mapValues { it }

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
