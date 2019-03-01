fun main(args: Array<String>) {
    tryInline({ param -> firstFun(param) }, { param -> println(param) }, 12)
}

fun firstFun(me: Int): String {
    return (me * me).toString()
}

private inline fun tryInline(crossinline funA: (Int) -> String, crossinline funB: (String) -> Unit, param: Int) {
    val resultA = funA(param)
    funB(resultA)
}