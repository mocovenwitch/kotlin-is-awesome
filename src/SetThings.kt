fun main(args: Array<String>) {
    mutableMapExample()
}

fun mutableMapExample() {
    val set: MutableMap<Int, MutableSet<String>> = mutableMapOf()

    set[1] = mutableSetOf("hello")
    set[1]?.add("me")

    val set2 = set[1]?.plus("me")

    set.forEach {
        it.value.forEach { value ->
            println("${it.key} + $value")
        }
    }

    println("-----------")

    set2?.forEach {
        println(it)

    }
}