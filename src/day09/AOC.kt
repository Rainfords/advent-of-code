package day09

import readInput

typealias HeightMap = Array<Array<Point>>

fun main() {
    fun part1(input: List<String>): Int {
        return heightMap(input).lowPoints().sumOf { it.value + 1 }
    }

    fun part2(input: List<String>): Int {
        val heightMap = heightMap(input)

        return heightMap.lowPoints()
            .map {
                heightMap.basinOf(it).count()
            }
            .sortedDescending()
            .take(3)
            .reduce { result, basin -> result * basin }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day09/sample")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("day09/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun heightMap(input: List<String>): HeightMap =
    input.mapIndexed { y, line ->
        line.mapIndexed { x, digit -> Point(x, y, digit.digitToInt()) }
            .toTypedArray()
    }
        .toTypedArray()

fun HeightMap.lowPoints() =
    flatten()
        .filter { point -> point < adjacent(point).minOf { it } }

fun HeightMap.adjacent(point: Point) =
    listOf(
        point.position.top(),
        point.position.right(),
        point.position.bottom(),
        point.position.left()
    )
        .mapNotNull { pointOf(it) }

fun HeightMap.pointOf(position: Position) =
    getOrNull(position.y)?.getOrNull(position.x)

fun HeightMap.basinOf(point: Point): Set<Point> {
    val moreBasin = adjacent(point)
        .filterNot { it.value == 9 }
        .filter { it > point }

    return if (moreBasin.isEmpty()) {
        setOf(point)
    } else {
        moreBasin.fold(setOf(point)) { basin, another -> basin + basinOf(another) }
    }
}

data class Position(val x: Int, val y: Int) {
    fun top() = Position(x, y - 1)
    fun right() = Position(x + 1, y)
    fun bottom() = Position(x, y + 1)
    fun left() = Position(x - 1, y)
}

data class Point(val position: Position, val value: Int) : Comparable<Point> {
    constructor(
        x: Int,
        y: Int,
        value: Int
    ) : this(
        Position(x, y),
        value
    )

    override fun compareTo(other: Point) = value.compareTo(other.value)
}