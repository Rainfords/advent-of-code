package y23.day01

import printResult
import readInput

fun main() {
    fun String.convertStringToNumber(): Int = when {
        contains("zero") -> 0
        contains("one") -> 1
        contains("two") -> 2
        contains("three") -> 3
        contains("four") -> 4
        contains("five") -> 5
        contains("six") -> 6
        contains("seven") -> 7
        contains("eight") -> 8
        contains("nine") -> 9
        else -> 0
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val first = line.find { it.isDigit() }
            val last = line.findLast { it.isDigit() }
            val value = Integer.valueOf("$first$last")
            sum += value
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach { str ->
            val lastIndex = str.lastIndex
            var firstTemp = ""
            var lastTemp = ""
            var isFindFirst = false
            var isFindLast = false

            str.indices.forEach { idx ->
                firstTemp += str[idx]
                lastTemp = str[lastIndex - idx] + lastTemp

                val firstNum = if (str[idx].isDigit()) str[idx].digitToInt()
                else firstTemp.convertStringToNumber()
                val lastNum = if (str[lastIndex - idx].isDigit()) str[lastIndex - idx].digitToInt()
                else lastTemp.convertStringToNumber()

                if (firstNum != 0 && isFindFirst.not()) {
                    sum += firstNum.times(10)
                    isFindFirst = true
                }
                if (lastNum != 0 && isFindLast.not()) {
                    sum += lastNum
                    isFindLast = true
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y23/day01/sample")
    val r1 = part1(testInput)
    val testInput2 = readInput("y23/day01/sample2")
    val r2 = part2(testInput2)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 142)
    check(r2 == 281)

    val input = readInput("y23/day01/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
