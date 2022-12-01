package y21.day05

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val vents = input.map {
            it.split(" -> ").map(Point::parse).run { SimpleVector(this[0], this[1]) }
        }

        return vents.flatten()
            .groupingBy { it }
            .eachCount()
            .filter { (_, times) -> times >= 2 }
            .count()
    }


    fun part2(input: List<String>): Int {
        val vents = input.map {
            it.split(" -> ").map(Point::parse).run { ComplexVector(this[0], this[1]) }
        }

        return vents.flatten()
            .groupingBy { it }
            .eachCount()
            .filter { (_, times) -> times >= 2 }
            .count()
    }

    val testInput = readInput("y21/day05/sample")
    with(part1(testInput)) { check(5 == this) { "result test 1: $this" } }
    with(part2(testInput)) { check(12 == this) { "result test 2: $this" } }

    val input = readInput("y21/day05/input")
    printResult("Part 1") { part1(input) }
    printResult("Part 2") { part2(input) }
}

data class SimpleVector(private val list: List<Point>) : List<Point> by list {
    constructor(a: Point, b: Point) : this(points(minOf(a, b), maxOf(a, b)))

    companion object {
        fun points(from: Point, to: Point) = when (true) {
            from.x == to.x -> (from.y..to.y).map { y -> Point(from.x, y) }
            from.y == to.y -> (from.x..to.x).map { x -> Point(x, from.y) }
            else -> emptyList()
        }
    }
}

data class ComplexVector(private val list: List<Point>) : List<Point> by list {
    constructor(a: Point, b: Point) : this(points(minOf(a, b), maxOf(a, b)))

    companion object {
        fun points(from: Point, to: Point) = when (true) {
            from.x == to.x -> (from.y..to.y).map { y -> Point(from.x, y) }
            from.y == to.y -> (from.x..to.x).map { x -> Point(x, from.y) }
            from.y < to.y -> (from.x..to.x).mapIndexed() { step, x -> Point(x, from.y + step) }
            else -> (from.x..to.x).mapIndexed() { step, x -> Point(x, from.y - step) }
        }
    }
}

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
        return if (x != other.x) x - other.x else y - other.y
    }

    companion object {
        fun parse(string: String) =
            string.split(",").let { (x, y) -> Point(x.toInt(), y.toInt()) }
    }
}
