fun main(args: Array<String>) {
    mutableMapExample()
}

fun mutableMapExample() {
    val set: MutableMap<Int, MutableSet<String>> = mutableMapOf()

    // this can be replaced by A
//    set[1] = mutableSetOf("hello")
//    set[1]?.add("me")
    // .

    // My name is A:
    set[1] = set.getOrDefault(1, mutableSetOf("hello")).plus("me").toMutableSet()
    //.

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