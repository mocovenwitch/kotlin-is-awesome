import HighOrderFun.heyYa

/**
 * high-order function, take function as a parameter, or return a function
 * - heyYa is my high-order function here
 */

fun main(args: Array<String>) {

    heyYa({ param -> firstFun(param) }, { param -> println(param) }, 8)
}

object HighOrderFun {

    fun firstFun(me: Int): String {
        return (me * me).toString()
    }

    fun heyYa(funA: (Int) -> String, funB: (String) -> Unit, param: Int) {
        val resultA = funA(param)
        funB(resultA)
    }
}