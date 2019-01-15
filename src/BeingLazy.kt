/**
 * Without delegation
 */
class LazyProperty(var initializer: () -> Int) {
    private var _lazy: Int? = null
    val lazy: Int
        get() {
            if (_lazy == null) {
                _lazy = initializer()
            }
            return _lazy!!
        }
}

/**
 * With delegation,
 *  lazy by default is in LazyThreadSafetyMode.SYNCHRONIZED mode
 */
class LazyProperty2(var initializer: () -> Int) {
    val lazy: Int by lazy {
        initializer()
    }
}


fun main(args: Array<String>) {
    val lazyMe = LazyProperty { ; 42 }
    println(lazyMe.lazy)

    val lazyMe2 = LazyProperty2 { ; 42 }
    println(lazyMe2.lazy)
}