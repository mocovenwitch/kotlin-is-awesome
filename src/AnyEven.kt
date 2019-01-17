/**
 * Collection.any {condition}
 *  any of the item meet condition, return true
 */
fun main(args: Array<String>) {
    val numbers = 0..9
    println("$numbers Contains even number: " + (if (numbers.any { it % 2 == 0 }) "true" else "false"))
}