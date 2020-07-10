import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue


fun main(args: Array<String>) {

    
    caller()
    
}

private fun caller() {
    val (value, duration) = measureTimedValue {
        println("get it running")
        longRun()
    }

    println("value = $value; duration= $duration ")
}

private fun longRun() {
    var sum = 0

    (1..1000000000).forEach {
        sum += it
    }
}