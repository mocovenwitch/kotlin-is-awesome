# Kotlin is Awesome

My study note of Kotlin. From basic to data structure and algorithm, might cover some usage in Android.

- [Kotlin Koan](#Kotlin-Koan)
    - [Join To String](#Join-To-String)
    - [Setter and Getter](#Setter-and-Getter)
    - [Lots of ways of being lazy](#Lots-of-ways-of-being-lazy)
    - [Any with Lambda](Any-with-Lambda)
    - [String pattern](#String-pattern)
    - [data class](#data-class)
    - [primitive types?](#primitive-types?)
- [Coroutines](#Coroutines)
    - [Kotlin Coroutines V.S. RxJava](#Kotlin-Coroutines-V.S.-RxJava)
    - [Kotlin Coroutines V.S. Threads](#Kotlin-Coroutines-V.S.-Threads)
    - [suspend fun](#suspend-fun)
    - [Job](#Job)
    - [launch & async](#launch-&-async)
    - [delay()](#delay())
    - [withContext()](#withContext())
    - [Channel](#Channel)
    - [Other](#Other)
- [Sealed class](#Sealed-class)

## Kotlin Koan
[Kotlin koans](ttps://github.com/Kotlin/kotlin-koans)

### Join To String

    fun task2(collection: Collection<Int>): String {
        return collection.joinToString(prefix = "{", postfix = "}")
    }

    assertEquals("{1, 2, 3, 42, 555}", task2(listOf(1, 2, 3, 42, 555)))

### Setter and Getter
`field` refers to the property itself. And in setter funtion, we can manipulate other properties.

    class PropertyExample() {
        var counter = 0
        var propertyWithCounter: Int? = null
            set(value) {
                field = value
                counter++
            }
    }

And also can go with just statement.

    class PropertyExample() {
        var counter = 0
        var propertyWithCounter: Int? = null
            get() = 42
    }

### Lots of ways of being lazy

`Without delegation`:

    class LazyProperty(val initializer: () -> Int) {
        var _lazy: Int? = null
        val lazy: Int
            get() {
                if (_lazy == null) {_lazy = initializer()}
                return _lazy!!
            }
    }

`With delegation`:

    class LazyProperty(val initializer: () -> Int) {
        val lazy: Int by lazy {
            initializer()
        }
    }

### Any with Lambda

Returns true if at least one element matches the given predicate.

    fun containsEven(collection: Collection<Int>): Boolean = collection.any { it % 2 == 0}

Example:

    val isEven: (Int) -> Boolean = { it % 2 == 0 }
    val zeroToTen = 0..10
    println("zeroToTen.any { isEven(it) } is ${zeroToTen.any { isEven(it) }}") // true
    println("zeroToTen.any(isEven) is ${zeroToTen.any(isEven)}") // true

    val odds = zeroToTen.map { it * 2 + 1 }
    println("odds.any { isEven(it) } is ${odds.any { isEven(it) }}") // false

    val emptyList = emptyList<Int>()
    println("emptyList.any { true } is ${emptyList.any { true }}") // false

### String pattern
Kotlin has two types of String
- one is escaped strings, which is similar to Java String, which have escaped characters
`val s = "Hello, world!\n"`;
- another one is raw string with `"""`, e.g.

        val text = """
                for (c in "foo")
                    print(c)
            """
which has newlines or other characters in.

And here is another good example to make pattern:

    val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"
    fun getPattern(): String = """\d{2} ${month} \d{4}"""

## data class
I know data class for a while but I dont know this untill today: the compiler automatically does for `data class User(val name: String, val age: Int)`

- generate `equals()` and `hashCode()`
- `toString()` of the form `"User(name=John, age=42)"`;

## primitive types?
Everything in Kotlin is an object. To user, all the numbers, strings, booleans are classes. But, there are some type have internal representation, e.g. numbers, characters, booleans can be represented as primitive type at runtime.



## Coroutines

Kotlin 1.1 introduced coroutines, a new way of writing asynchronous, non-blocking code.

### Kotlin Coroutines V.S. RxJava
The two main purposes I started using RxJava are:
 - We can do streaming programming by using RxJava, thinking data as streams, and everything can be an Observable, and we subscribe on it and wait for it on main thread, and then do the rest of work
 - We can manage threadings easier, compare to using Async... and other threading API in Android

Then why do I want to try Coroutines?
 - It is Kotlin own feature, if I can replace Rx with it, less dependencies to my project
 - Simpler code, easy to learn
 - Less threads costing (thread is resource)

### Kotlin Coroutines V.S. Threads
 - Kotlin coroutines can run in one thread or different threads, or in a shared thread pool
 - Coroutines runs cooperatively multitasking; Thread runs preemptively multitasking

Have some readings:
- [Coroutines basic](https://kotlinlang.org/docs/tutorials/coroutines/coroutines-basic-jvm.html)
- [Coroutines vs Threads](https://stackoverflow.com/questions/43021816/difference-between-thread-and-coroutine-in-kotlin)


### suspend fun
A method which need to run at coroutines.

### Job

### launch & async

    launch {
        ...
    }
Just by doing this, we started a new coroutines. By default, it runs in a shared threads pool. One thread can run many coroutines.

    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async {
            n
        }
    }
Async returns a value from coroutines.

### delay()
`delay()` only suspend itself - the current coroutines, does not block any thread. `delay()` cannot be called from main thread, since it can only be called from coroutines or other suspend function.

### withContext()

    withContext(IO) {
        ...
    }

    withContext(Default) {
        ...
    }

 - `IO`: that is designed for offloading blocking IO tasks to a shared pool of threads. This dispatcher `shares threads with a Dispatchers.Default` dispatcher. `withContext(Dispatchers.IO) { ... }` does not lead to an actual switching to another thread
 - `Default`: The default dispatcher, that is used when coroutines are launched in `GlobalScope`, is represented by `Dispatchers.Default` and uses shared background pool of threads, so `launch(Dispatchers.Default) { ... }` uses the same dispatcher as `GlobalScope.launch { ... }`. `Global scope` is used to launch top-level coroutines which are operating on the `whole application lifetime` and are not cancelled prematurely.



### Channel
`Deferred` values provide a convenient way to transfer a single value between coroutines. `Channels` provide a way to transfer a stream of values.

    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        channel.close() // we're done sending
    }
    // here we print received values using `for` loop (until the channel is closed)
    for (y in channel) println(y)
    println("Done!")

### Other
`org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1` requires Kotlin version 1.3 and above.


## Sealed class
Sealed class is abstract class can't be initialied directly.

The benefit of sealed class:
- when a value can have one of the types from a limited set, make value more restriced.
- less code compare to use normal class
- easily use with `when()`

<b>Example</b>:

    /* my example */

    sealed class Response<MODEL>

    class ResponseSuccess<MODEL>(val data: MODEL) : Response<MODEL>()

    class ResponseLoading<MODEL> : Response<MODEL>()

    class ResponseError<MODEL>(val error: Throwable?) : Response<MODEL>()

<b>Use with `when()`</b>

    when (response) {
        is ResponseSuccess -> {
            ...
        }
        is ResponseLoading -> {
            ...
        }
        is ResponseError -> {
            ...
        }
    }


[A good article](https://proandroiddev.com/kotlin-coroutines-patterns-anti-patterns-f9d12984c68e)

[BACK](#Kotlin-is-Awesome)
