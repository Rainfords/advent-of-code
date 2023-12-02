package y22.day18

import printResult
import readInput
import java.util.*

fun main() {
    fun List<Int>.neighbors() = listOf(-1, 1).flatMap {
        listOf(
            listOf(get(0) + it, get(1), get(2)),
            listOf(get(0), get(1) + it, get(2)),
            listOf(get(0), get(1), get(2) + it)
        )
    }

    fun surfaceArea(scannedLavaDroplet: List<Triple<Int, Int, Int>>): Int {
        // Create a set of all the cubes in the scanned lava droplet
        val cubes = mutableSetOf<Triple<Int, Int, Int>>()
        for (cube in scannedLavaDroplet) {
            cubes.add(cube)
        }

        // Initialize a variable to store the total surface area
        var totalSurfaceArea = 0

        // Iterate through each cube in the set
        for (cube in cubes) {
            val (x, y, z) = cube
            // Check if the cubes adjacent to the current cube are present in the set
            if (Triple(x - 1, y, z) !in cubes) {
                totalSurfaceArea += 1
            }
            if (Triple(x + 1, y, z) !in cubes) {
                totalSurfaceArea += 1
            }
            if (Triple(x, y - 1, z) !in cubes) {
                totalSurfaceArea += 1
            }
            if (Triple(x, y + 1, z) !in cubes) {
                totalSurfaceArea += 1
            }
            if (Triple(x, y, z - 1) !in cubes) {
                totalSurfaceArea += 1
            }
            if (Triple(x, y, z + 1) !in cubes) {
                totalSurfaceArea += 1
            }
        }

        // Return the total surface area
        return totalSurfaceArea
    }

    fun exteriorSurfaceArea(scannedLavaDroplet: List<Triple<Int, Int, Int>>): Int {
        // Create a set of all the cubes in the scanned lava droplet
        val cubes = mutableSetOf<Triple<Int, Int, Int>>()
        for (cube in scannedLavaDroplet) {
            cubes.add(cube)
        }

        // Create a queue for the BFS algorithm
        val queue = mutableListOf<Triple<Int, Int, Int>>()

        // Add the cubes on the exterior of the lava droplet to the queue
        for (cube in cubes) {
            val (x, y, z) = cube
            if (Triple(x - 1, y, z) !in cubes) {
                queue.add(cube)
            }
            if (Triple(x + 1, y, z) !in cubes) {
                queue.add(cube)
            }
            if (Triple(x, y - 1, z) !in cubes) {
                queue.add(cube)
            }
            if (Triple(x, y + 1, z) !in cubes) {
                queue.add(cube)
            }
            if (Triple(x, y, z - 1) !in cubes) {
                queue.add(cube)
            }
            if (Triple(x, y, z + 1) !in cubes) {
                queue.add(cube)
            }
        }

        // Initialize a set to keep track of the cubes that have been visited
        val visited = mutableSetOf<Triple<Int, Int, Int>>()

        // Initialize a variable to store the total surface area
        var totalSurfaceArea = 0

        // Run the BFS algorithm
        while (queue.isNotEmpty()) {
            val cube = queue.removeAt(0)
            if (cube in visited) {
                continue
            }
            visited.add(cube)
            val (x, y, z) = cube
            if (Triple(x - 1, y, z) !in cubes) {
                totalSurfaceArea += 1
                queue.add(Triple(x - 1, y, z))
            }
            if (Triple(x + 1, y, z) !in cubes) {
                totalSurfaceArea += 1
                queue.add(Triple(x + 1, y, z))
            }
            if (Triple(x, y - 1, z) !in cubes) {
                totalSurfaceArea += 1
                queue.add(Triple(x, y - 1, z))
            }
            if (Triple(x, y + 1, z) !in cubes) {
                totalSurfaceArea += 1
                queue.add(Triple(x, y + 1, z))
            }
            if (Triple(x, y, z - 1) !in cubes) {
                totalSurfaceArea += 1
                queue.add(Triple(x, y, z - 1))
            }
        }
        return totalSurfaceArea
    }

    fun part1(input: List<String>): Int {
        val scannedLavaDroplet = mutableListOf<Triple<Int, Int, Int>>()
        input.forEach { line ->

            val coordinates = line.split(",")
            val x = coordinates[0].toInt()
            val y = coordinates[1].toInt()
            val z = coordinates[2].toInt()
            scannedLavaDroplet.add(Triple(x, y, z))
        }
        return surfaceArea(scannedLavaDroplet)
    }

    fun part2(input: List<String>): Int {
        val cubes = input.map { line -> line.split(",").map { it.toInt() } }
        val inside = mutableSetOf<List<Int>>()
        val outside = mutableSetOf<List<Int>>()
        return cubes.sumOf {
            it.neighbors().count { cube ->
                if (cube in outside)
                    return@count true
                if (cube in inside)
                    return@count false
                val seen = mutableSetOf<List<Int>>()
                val q: Queue<List<Int>> = LinkedList()
                q.add(cube)
                while (q.isNotEmpty()) {
                    val c = q.poll()
                    if (c in cubes || c in seen)
                        continue
                    seen += c
                    if (seen.size > 2000) {
                        outside += seen
                        return@count true
                    }
                    q.addAll(c.neighbors())
                }
                inside += seen
                false
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y22/day18/sample")
    val r1 = part1(testInput)
    val r2 = part2(testInput)
    printResult("#1 is: ") { r1 }
    printResult("#2 is: ") { r2 }
    check(r1 == 64)
    check(r2 == 58)

    val input = readInput("y22/day18/input")
    println("Part 1 PASSED: ${part1(input)}")
    println("Part 2 PASSED: ${part2(input)}")
}
