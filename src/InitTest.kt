import kotlin.properties.Delegates

object MyInitClass {
    private lateinit var instance: String

    fun isInited() :Boolean{
        return this::instance.isInitialized
    }

    fun init() {
        instance = "here"
    }
}

fun main(args: Array<String>) {
    println(MyInitClass.isInited())
    MyInitClass.init()
    println(MyInitClass.isInited())
}