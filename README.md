# Kotlin is Awesome

My study note of Kotlin. From basic to [data structure and algorithm](./DSA.md), might cover some usage in Android.

- [Kotlin Koan](#Kotlin-Koan)
    - [Join To String](#Join-To-String)
    - [Setter and Getter](#Setter-and-Getter)
    - [Lots of ways of being lazy](#Lots-of-ways-of-being-lazy)
    - [Any with Lambda](#Any-with-Lambda)
    - [String pattern](#String-pattern)
    - [data class](#data-class)
    - [primitive types](#primitive-types)
    - [list and array](#list-and-array)
    - [zip](#zip)
- [Coroutines](#Coroutines)
    - [Kotlin Coroutines VS RxJava](#Kotlin-Coroutines-VS-RxJava)
    - [Kotlin Coroutines VS Threads](#Kotlin-Coroutines-VS-Threads)
    - [suspend fun](#suspend-fun)
    - [Job](#Job)
    - [launch & async](#launch-&-async)
    - [delay](#delay)
    - [withContext](#withContext)
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

By default, the evaluation of lazy properties is synchronized: the value is computed only in one thread, and all threads will see the same value.

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

[2019-2-5 update]
Compiler generates equals() and hashCode() for data class, but if we use array in it, we need to override it, since:

In Kotlin there are two types of equality:
- Structural equality == (a check for equals(), only check content)
- Referential equality === (two references point to the same object);

Array equals is not structually equals by default. Why?! JVM!

`Root Cause`
It’s a long-standing well-known issue on the JVM: equals() works differently for arrays and collections. `Collections are compared structurally, while arrays are not`, equals() for them simply resorts to referential equality: this === other.
Currently, Kotlin data classes are ill-behaved with respect to this issue:
- if you declare a component to be an array, it will be compared structurally,
- but if it is a multidimensional array (array of arrays), the subarrays will be compared referentially (through equals() on arrays),
and if the declared type of a component is Any or T, but at runtime it happens to be an array, equals() will be called too.

This behavior is inconsistent. [Read](https://blog.jetbrains.com/kotlin/2015/09/feedback-request-limitations-on-data-classes/) and [equality](https://kotlinlang.org/docs/reference/equality.html)

`Example` [DataEquals](https://github.com/mocovenwitch/kotlin-is-awesome/blob/master/src/DataEquals.kt) print out false when the data class includes a array in it.

`Solution`
Make it a List other than Array.



## primitive types
Everything in Kotlin is an object. To user, all the numbers, strings, booleans are classes. But, there are some type have internal representation, e.g. numbers, characters, booleans can be represented as primitive type at runtime.

## list and array

`list` in kotlin is immutable, if we want to update it, should use mutableList.

`array` in kotlin has .plus operation, it is easy to copy or combine two array.

## zip
Returns a list of values built from the elements of `this` collection and the [other] collection with the same index
 * using the provided [transform] function applied to each pair of elements.
 * The returned list has length of the shortest collection.

Let have an `example`:

[CoolZip](https://github.com/mocovenwitch/kotlin-is-awesome/blob/master/src/CoolZip.kt)

`Kotlin Source code`

    public inline fun <T, R, V> Iterable<T>.zip(other: Iterable<R>, transform: (a: T, b: R) -> V): List<V> {
        val first = iterator()
        val second = other.iterator()
        val list = ArrayList<V>(minOf(collectionSizeOrDefault(10), other.collectionSizeOrDefault(10)))
        while (first.hasNext() && second.hasNext()) {
            list.add(transform(first.next(), second.next()))
        }
        return list
    }

`Time Complexity`
If list one is size N (short one), list two is size M (large one), big-O is
 - zip
    - while is `O(N)`
    - list.add is O(1) if add at the end and list not resize, but it grows up if resize the list
 - foreach print O(N)

 O(N) + O(N) is still O(N)

`Space Complexity`
Since it creates another list in zip function, and starts with the list size 10, we could expect more space cost

`So we can say zip function is not that good in terms of time and space efficiency. But it makes good looking code.`

## Flat map by type Any
Flat a map is easy to understand, but one day I have a linked hash map: LinkedHashMap<Int, Any>, value can be an object or a list of objects. I wanted flat function can flat all the objects into one level list no matter what.

    val map = LinkedHashMap<String, Any>()
    map["1"] = listOf("one", "two", "three")
    val result = map.values.flatMap{listOf(it)} 

The result size is one, which means it doesn't flat the map as I wish.

While it simply works if I do `val map = LinkedHashMap<String, List<Any>>()`

[map flat example](https://github.com/mocovenwitch/kotlin-is-awesome/blob/master/src/MapToList.kt)

## Coroutines

Kotlin 1.1 introduced coroutines, a new way of writing asynchronous, non-blocking code.

### Kotlin Coroutines VS RxJava
The two main purposes I started using RxJava are:
 - We can do streaming programming by using RxJava, thinking data as streams, and everything can be an Observable, and we subscribe on it and wait for it on main thread, and then do the rest of work
 - We can manage threadings easier, compare to using Async... and other threading API in Android

Then why do I want to try Coroutines?
 - It is Kotlin own feature, if I can replace Rx with it, less dependencies to my project
 - Simpler code, easy to learn
 - Less threads costing (thread is resource)

### Kotlin Coroutines VS Threads
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

### delay
`delay()` only suspend itself - the current coroutines, does not block any thread. `delay()` cannot be called from main thread, since it can only be called from coroutines or other suspend function.

### withContext

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
