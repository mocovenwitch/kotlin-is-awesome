fun main(args: Array<String>) {
    notReallyFlatten()
    reallyFlatten()
}

/**
 * the value type is Any, make flatMap doesn't understand what type should flat by
 */
private fun notReallyFlatten() {
    val map = LinkedHashMap<String, Any>()
    map["1"] = listOf("one", "two", "three")
    val result = map.values.flatMap{listOf(it)} 
    println("not really flatten:" + result + " size is:" + result.size)
}

/**
 * works
 */
private fun reallyFlatten() {
    val map = LinkedHashMap<String, List<Any>>()
    map["1"] = listOf("one", "two", "three")
    map["2"] = listOf(1, 2, 3)
    val result = map.values.flatMap{it}
    println("really flatten:" + result + " size is:" + result.size )
}