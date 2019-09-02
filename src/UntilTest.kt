/**
 * No obvious performance difference observed.
 */
object UntilTest {
    fun test1() {
        val range = 999999999
        val start = System.currentTimeMillis()
        var sum : Int = 0

        for (i in 0..(range-1)) {
            sum += i
        }

        System.out.println("${System.currentTimeMillis() - start} - $sum")
    }

    fun test2() {
        val range = 999999999
        val start = System.currentTimeMillis()
        var sum : Int = 0

        for (i in 0 until range) {
            sum += i
        }

        System.out.println("${System.currentTimeMillis() - start} - $sum")
    }
}

fun main(args: Array<String>) {
    UntilTest.test1()
    UntilTest.test2()
}