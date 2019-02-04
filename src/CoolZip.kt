fun main(args: Array<String>) {

    val list1 = listOf(1, 2, 3)
    val list2 = listOf("one", "two", "three")

    val zip = list1.zip(list2)

    // print out 1 one ,2 two ,3 three ,
    zip.forEach {print("${it.first} ${it.second} ,")}
    
}