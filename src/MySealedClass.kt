/**
 * https://kotlinlang.org/docs/reference/sealed-classes.html
 * `Note that classes which extend subclasses of a sealed class (indirect inheritors) can be placed anywhere, not necessarily in the same file.`
 *
 * and here is the proposal:
 * https://github.com/Kotlin/KEEP/blob/master/proposals/sealed-class-inheritance.md
 */

abstract class NormalClass(val my: String)
//sealed class SealedClass(val my: String) // need to be with its children

class MySealedClass : AClass("in another file")
