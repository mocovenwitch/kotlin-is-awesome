data class NumArray(val name: String, val values: IntArray)
data class NumList(val name: String, val values: List<Int>)
fun main(args: Array<String>) {
    goWithArray()
    goWithList()
}

fun goWithArray() {
    val n1 = NumArray("1", intArrayOf(1,2,3,4))
    val n2 = NumArray("1", intArrayOf(1,2,3,4))
    val result = n1==n2
    println("Arrays equal? $result")
}

fun goWithList() {
    val n1 = NumList("1", listOf(1,2,3,4))
    val n2 = NumList("1", listOf(1,2,3,4))
    val result = n1==n2
    println("Lists equal? $result")
}