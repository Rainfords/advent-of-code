package day04

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val numbers = input
            .first()
            .split(",")
            .map { it.toInt() }

        val boards = input
            .drop(1)
            .chunked(6)
            .map { it.drop(1) }
            .map { Board(it) }

        var firstCompletedBoardScore: Int? = null

        out@ for (number in numbers) {
            for (board in boards) {
                if (board.mark(number)) {
                    firstCompletedBoardScore = board.score(number)
                    break@out
                }
            }
        }

        return firstCompletedBoardScore!!
    }


    fun part2(input: List<String>): Int {
        val numbers = input
            .first()
            .split(",")
            .map { it.toInt() }

        val boards = input
            .drop(1)
            .chunked(6)
            .map { it.drop(1) }
            .map { Board(it) }

        var lastCompletedBoardScore: Int? = null

        numbers.map { number ->
            boards.filterNot(Board::completed).map { board ->
                if (board.mark(number)) {
                    lastCompletedBoardScore = board.score(number)
                }
            }
        }

        return lastCompletedBoardScore!!
    }

    val testInput = readInput("day04/sample")
    with(part1(testInput)) { check(4512 == this) { "result test 1: $this" } }
    with(part2(testInput)) { check(1924 == this) { "result test 2: $this" } }

    val input = readInput("day04/input")
    printResult("Part 1") { part1(input) }
    printResult("Part 2") { part2(input) }
}

class Board private constructor(val numbers: List<List<Int>>) : List<List<Int>> by numbers {
    private var completed = false
    private val markedRows = Array(5) { arrayOfNulls<Int>(5) }
    private val markedColumns = Array(5) { arrayOfNulls<Int>(5) }

    fun mark(number: Int): Boolean {
        val position = numbers.flatten().indexOf(number)

        if (position != -1) {
            markedRows[position / 5][position % 5] = number
            markedColumns[position % 5][position / 5] = number
            completed = (markedRows + markedColumns).any() { it.filterNotNull().size == 5 }
        }

        return completed
    }

    fun score(winner: Int) = winner * (numbers.flatten().sum() - markedRows.flatten().filterNotNull().sum())

    fun completed() = completed

    companion object {
        operator fun invoke(lines: List<String>) =
            Board(lines.map { line -> line.trim().split("\\D+".toRegex()).map { number -> number.toInt() } })
    }
}