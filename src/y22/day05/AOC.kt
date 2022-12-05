package y22.day05

import readInput
import java.util.*

fun main() {
    fun part1(input: List<String>): String {
        val stack = makeSupplyStacks(input)
        val moveData = getMoveData(input)
        moveData.forEach { row ->
            for (count in 1..row.count) {
                val crate = stack[row.from]?.pop()
                stack[row.to]?.push(crate)
            }
        }

        return stack.values.map { it.last() }.joinToString("")
            .replace("[", "")
            .replace("]", "")
    }

    fun part2(input: List<String>): String {
        val stack = makeSupplyStacks(input)
        val moveData = getMoveData(input)
        moveData.forEach { row ->
            var tmpStack = mutableListOf<String>()
            for (count in 1..row.count) {
                val crate = stack[row.from]?.pop()
                tmpStack.add(crate!!)
            }
            tmpStack.reverse()
            tmpStack.map { stack[row.to]?.push(it) }
        }

        return stack.values.map { it.last() }.joinToString("")
            .replace("[", "")
            .replace("]", "")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day05/sample")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("y22/day05/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun makeSupplyStacks(input: List<String>): Map<Int, Stack<String>> {
    val numberOfStacks = stackWidth(input)
    val heightOfStacks = stackHeight((input))
    val stacks: MutableMap<Int, Stack<String>> = mutableMapOf()
    for (row in heightOfStacks - 1 downTo 0) {
        val stacksRow = input[row].chunked(4) { stack -> stack.trim().toString() }
        for (stack in 0 until numberOfStacks) {
            if (stacks[stack] == null) {
                stacks[stack] = Stack<String>()
            }
            if (stacksRow.size > stack && stacksRow[stack].isNotBlank()) {
                stacks[stack]?.push(stacksRow[stack])
            }
        }
    }
    return stacks
}

fun getMoveData(input: List<String>): List<MoveData> {
    //move 1 from 1 to 2
    val data = mutableListOf<MoveData>()
    val firstRow = input.indexOfFirst { it.isBlank() } + 1
    for (row in firstRow until input.size) {
        val moveData = input[row].split(" ")
        data.add(
            MoveData(
                count = moveData[1].toInt(),
                from = moveData[3].toInt() - 1,
                to = moveData[5].toInt() - 1
            )
        )
    }
    return data
}

fun stackWidth(input: List<String>) = input.first { !it.contains("[") }
    .split(" ")
    .last()
    .toInt()

fun stackHeight(input: List<String>) = input.indexOfFirst { !it.contains("[") }

data class MoveData(
    val count: Int,
    val from: Int,
    val to: Int
)