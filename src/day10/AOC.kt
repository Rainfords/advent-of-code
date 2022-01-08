package day10

import readInput
import java.util.*

fun calcError(line: String): Int {
    val stack = Stack<Char>()
    for (c in line) {
        when (c) {
            '(', '{', '[', '<' -> stack.push(c)
            ')' -> if (stack.pop() != '(') return 3
            ']' -> if (stack.pop() != '[') return 57
            '}' -> if (stack.pop() != '{') return 1197
            '>' -> if (stack.pop() != '<') return 25137
        }
    }
    return 0
}

fun autocompleteScore(line: String): Long? {
    val stack = Stack<Char>()
    var total = 0L
    for (c in line) {
        when (c) {
            '(', '{', '[', '<' -> stack.push(c)
            ')' -> if (stack.pop() != '(') return null
            ']' -> if (stack.pop() != '[') return null
            '}' -> if (stack.pop() != '{') return null
            '>' -> if (stack.pop() != '<') return null
        }
    }
    while (!stack.empty()) {
        when (stack.pop()) {
            '(' -> total = total * 5 + 1
            '[' -> total = total * 5 + 2
            '{' -> total = total * 5 + 3
            '<' -> total = total * 5 + 4
        }
    }
    return total
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { calcError(it) }
    }

    fun part2(input: List<String>): Long {
        val total = input.mapNotNull { autocompleteScore(it) }
        return total.sorted()[total.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day10/sample")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("day10/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
