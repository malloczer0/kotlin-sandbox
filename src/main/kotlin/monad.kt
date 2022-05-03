interface IMonad<A> {
    // монада является функтором, но map реализовывать отдельно не нужно -
    //   он выражается через flatMap и lift
    //fun <B> map(f: (A) -> B): IMonad<B> = flatMap { a -> lift(f(a)) }
    fun <B> flatMap(f: (A) -> IMonad<B>): IMonad<B>
}
fun <A> IMonad<A>.lift(value: A): IMonad<A> = TODO()

// a monad as is
class Monad<T>() {
    // can't be lateinit
    var t: T? = null
    lateinit var f: (t: T) -> T
    //just two of constructors
    constructor(f: (t: T) -> T): this() { this.f = f }
    constructor(t: T): this() { this.t = t }
    // it's what our constructor do
    // can be still useful. maybe. maybe not.
    // this function 'lifts up' a value t to a Monad class
    fun lift(t: T): Monad<T> = Monad { t }  // using a secondary constructor here. cause we can to

    fun <B> flatMap(f: (T) -> Monad<B>) = run {
        f(t!!)
    }
    
}

// testing
fun main(args: Array<String>) {
    listOf<Any>()
    val monad = Monad<Unit>()
}