fun isOdd(x: Int) = x % 2 != 0

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))
    println(numbers.filter { isOdd(it) })
}