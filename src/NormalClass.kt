/**
 * What's the difference??
 */

// sealed class and its children need to be all together in a file, or nested
sealed class SealedClass(val my: String)

open class AClass(val value: String) : SealedClass(value + "seal")

class BClass(val value: Int) : SealedClass(value.toString() + "seal")

// normal class can be in another file
class CClass(val value: String) : NormalClass(value + "my")

class DClass(val value: Int) : NormalClass(value.toString() + "my")

fun main() {
    val a = AClass("hi")
    val b = BClass(1)
    val aa = MySealedClass()

    val c = CClass("hi")
    val d = DClass(1)

    printNormal(c)
    printNormal(d)

    printForSealedClass(a)
    printForSealedClass(b)
    printForSealedClass(aa)
}

fun printNormal(o: NormalClass) {
    when (o) {
        is CClass -> println(o.my + o.value)
        is DClass -> println(o.my + o.value)
        else -> {
        } // normal class can have else
    }
}

fun printForSealedClass(o: SealedClass) {
    when (o) {
        is AClass -> println(o.my + o.value)
        is BClass -> println(o.my + o.value)
//        is MySealedClass -> {} // if there is no this branch, else is still redundant
        else -> {
        } // sealed class is exhaustive, else is redundant
    }
}