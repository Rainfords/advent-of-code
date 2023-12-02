package y22.day13

import printResult
import readInput

fun main() {

    fun deserialize(s: String): Any {
        if ("[" !in s)
            return s.toInt()
        var str = ""
        var c = 0
        val arr = mutableListOf<Any>()
        for (ch in s.drop(1).dropLast(1)) {
            if (ch == '[') c++
            if (ch == ']') c--
            if (c != 0 || ch != ',')
                str += ch
            else
                arr += deserialize(str).also { str = "" }
        }
        if (str.isNotEmpty())
            arr += deserialize(str)
        return arr
    }

    fun compare(a: Any, b: Any): Int {
        return when {
            a is String && b is String -> compare(deserialize(a), deserialize(b))
            a is Int && b is Int -> if (a < b) -1 else if (a > b) 1 else 0
            a is Int -> compare(listOf(a), b)
            b is Int -> compare(a, listOf(b))
            else -> {
                a as List<Any>
                b as List<Any>
                for (i in 0 until minOf(a.size, b.size)) {
                    compare(a[i], b[i]).let { if (it != 0) return it }
                }
                compare(a.size, b.size)
            }
        }
    }

    fun part1(input: List<String>): Int {
        val pairs = input.chunked(3)
        return pairs.indices.sumOf { if (compare(pairs[it][0], pairs[it][1]) == -1) it + 1 else 0 }
    }

    fun part2(input: List<String>): Int {
        val sorted = (input.filter { it != "" } + listOf("[[2]]", "[[6]]")).sortedWith(::compare)
        return (sorted.indexOf("[[2]]") + 1) * (sorted.indexOf("[[6]]") + 1)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day13/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 13)
    check(r2 == 140)

    val input = readInput("y22/day13/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
