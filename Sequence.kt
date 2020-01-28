

fun main(args: Array<String>) {
    val mySet = mutableSetOf(1, 2, 3, 4)
    mySet.add(5)
    
    println(mySet.asSequence().take(3).toList())
    println(mySet.toList().takeLast(3))
}

// prints [1,2,3]