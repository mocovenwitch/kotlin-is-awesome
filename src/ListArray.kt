/**
 * Unlike java, if we want to update the list, we need to use mutable list in Kotlin
 *  - set: list[index] = value
 *  - add: at the end
 *  - remove: remove and shift all the right items left
 */
class MyItem(val value: String?)

fun listExample() {
    // list
    val list = mutableListOf("zero", "five", "two")
    list[1] = "one"
    list.add("end")

    // output: [zero, one, two, end]
    println(list)

    list.removeAt(2)
    // [zero, one, end]
    println(list)
}

fun arrayExample() {
    // Array, init with size
    var array = Array(3) { MyItem(null) }
    array.forEach { print(it.value) }

    println()

    // Array, update an item
    array[0] = MyItem("1")

    // Array, add more item
    array = array.plus(MyItem("more"))
    array.forEach { print(it.value) }

    // Array filter out all nulls and return a list
    println()
    array.filter { it.value != null }.forEach{ print(it.value)}
}

fun main(args: Array<String>) {
    listExample()
    arrayExample()
}