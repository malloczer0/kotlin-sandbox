

val multiply3: (Int, Int, Int) -> Int = { i1: Int, i2: Int, i3: Int -> i1*i2*i3 }
// curry multiply3
fun <R>f(r: R): (R) -> (Int) -> R = fun(b: R) = fun(c: Int) = r

val multiply3curried : (Int) -> (Int) -> (Int) -> Int = {
        a: Int -> { b: Int -> { c: Int -> multiply3(a,b,c) } } }

fun multiply3curried_(a: Int) = fun(b: Int) = fun(c: Int) = multiply3(a,b,c)

fun fg(x: String, f: (String) -> Unit, g: (String) -> Unit) = run {
    with(x) {
        run(f)
        run(g)
    }
}

fun fn(x: String, vararg fs: (String) -> Unit) = run {
    with(x) {
        fs.forEach {
            run(it)
        }
    }
}

fun g(a: Int, b: Int, c: Int) = a+b+c
fun ga(b: Int, c: Int) = g(1, b, c)
val ga_ = { b: Int, c: Int -> g(1, b, c) }

fun <R>t(f: (R) -> (R) -> (Int) -> R, r: R) = run {
    t_(f(r))
}
fun <R>t_(f: (R) -> (Int) -> R) = run {
    f(1)
}

fun main() {

    multiply3curried(1)(2)(3).also(::println)
    val m = multiply3curried(1)
    multiply3curried_(1)(2)
    m(2)(3)
    val qwerty = fun(a: Int) { }
    qwerty(1)

    fun fn_(fs: (String) -> Unit) = fn("", fs)


    fg("fg", ::print, ::println)
    fg("fg", ::print) { print(it) }
    fn("fn", ::println, ::println)

    t(::f, "")
}