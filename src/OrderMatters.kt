class OrderMatters() {

    // val result = if(isValid) 1 else 0 // if this line is here, compiles but throw exception "error: variable 'isValid' must be initialized"

    val isValid = true

    val result = if(isValid) 1 else 0

}

fun main(args: Array<String>) {
    println("Kotlin class: ${OrderMatters().result}")
}