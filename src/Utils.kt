import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()
fun readInputAsInts(name: String) = File("src", "$name.txt").readLines().map { it.toInt() }
fun readInputAsInts(name: String, delimiter: String) =
    File("src", "$name.txt").readLines().first().split(delimiter).toInts()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Print the result of the block next line after the title
 */
fun printResult(title: String, block: () -> Any) {
    println("Result $title")
    println(block())
}

/**
 * Convert a given list of strings to integers
 */
fun List<String>.toInts() = map { it.toInt() }