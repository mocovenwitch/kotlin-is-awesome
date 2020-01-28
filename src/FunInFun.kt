object FunInFun {
    fun sayHi() {
        fun say() {
            System.out.println("hi")
        }
        say()
    }


}

fun main() {
    FunInFun.sayHi()
}