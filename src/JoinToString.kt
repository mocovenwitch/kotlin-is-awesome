/**
 * Collection.joinToString()
 *
 * And...
 *  There is no:
 *      public static void main(String args[]) {}
 *
 * Only a main function:
 *      fun main(args: Array<String>) {}
 */
fun main(args: Array<String>) {
    val names = arrayOf("Mocoven", "Lene", "Wei")

    println(names.joinToString(prefix = "I am ", separator = " and ", postfix = ". "))

    // default separator is ,
    println(names.joinToString(prefix = "I am ", postfix = ". "))
}
