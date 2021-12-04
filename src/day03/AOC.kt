package day03

import readInput

data class Count(
    var zeros: Int = 0,
    var ones: Int = 0
)


fun main() {
    fun part1(input: List<String>): Int {
        var gammaRate = ""
        var epsilonRate = ""
        for (pos in 0 until input[0].length) {
            var countOfOnes = 0
            var countOfZeros = 0
            input.forEach {
                val digit = it.substring(pos, pos + 1).toInt()
                if (digit == 1) countOfOnes++
                if (digit == 0) countOfZeros++
            }
            gammaRate += if (countOfOnes > countOfZeros) "1" else "0"
            epsilonRate += if (countOfOnes < countOfZeros) "1" else "0"

        }
        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun part2(input: List<String>): Int {
        val oxygenRatingRaw = findRating(input, 0) { value, position, count ->
            val valueAtPosition = value[position]
            valueAtPosition == findOxygenBit(count)
        }
        val oxygenRating = Integer.parseInt(oxygenRatingRaw, 2)
        println("oxygen $oxygenRatingRaw -> $oxygenRating")

        val co2RatingRaw = findRating(input, 0) { value, position, count ->
            val valueAtPosition = value[position]
            valueAtPosition == findCo2Bit(count)
        }
        val co2Rating = Integer.parseInt(co2RatingRaw, 2)
        println("co2 $co2RatingRaw -> $co2Rating")

        return oxygenRating * co2Rating
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/sample")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("day03/input")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun findCount(values: List<String>, position: Int): Count {
    val count = Count()
    values.forEach { line ->
        val character = line[position]
        when (character) {
            '0' -> count.zeros++
            '1' -> count.ones++
        }
    }
    return count
}

fun findRating(
    values: List<String>,
    position: Int,
    filter: (String, Int, Count) -> Boolean
): String {
    val count = findCount(values, position)
    val filteredValues = values.filter { value ->
        filter(value, position, count)
    }
    return if (filteredValues.size == 1) {
        filteredValues[0]
    } else if (filteredValues.size > 1) {
        findRating(filteredValues, position + 1, filter)
    } else {
        throw IllegalStateException("Could not find values")
    }
}

fun findOxygenBit(count: Count): Char {
    return if (count.ones > count.zeros) '1' else if (count.zeros > count.ones) '0' else '1'
}

fun findCo2Bit(count: Count): Char {
    return if (count.ones < count.zeros) '1' else if (count.zeros < count.ones) '0' else '0'
}
