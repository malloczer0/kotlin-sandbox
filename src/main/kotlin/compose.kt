class Some {
    constructor(other: Other)
    companion object {
        fun create(f: () -> Other) = Some(f())
        inline fun <T>create(t: T? = null, f: (T?) -> Other) = Some(f(t))
        fun doSome(any: Any) = run {}
    }
    fun Other.extention() = run {  }
    fun foo() = run {}
}

class Other() {
    fun doStuff() = run { Other() }
    companion object {
        fun create() = Other()
    }
}


fun Other.doStuff() = run { }
val doStuff: Other.() -> Unit = {  }

fun main(args: Array<String>) {
    //Some.create(Other::create)
    Some.create(Other) { it!!.create().doStuff() }
    //(5).let(Some::doSome)
    Other().doStuff().also(::println)
    Other().run(doStuff)
}