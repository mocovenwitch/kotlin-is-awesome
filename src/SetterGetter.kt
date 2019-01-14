/**
 * Setter and Getter
 */
class Person(private var _name: String) {

    val name: String
        get() = _name

    var ageChanged = 0
    var age: Int = 0
        set(value) {
            field = value
            ageChanged++
        }

    init {
        age = 0
    }
}

fun main(args: Array<String>) {
    val person = Person("Mocoven")
    println("${person.name} is ${person.age} yo")

    person.age = 9
    println("ageChanged to ${person.age}")
}