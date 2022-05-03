// fun as as val,
// actually a factory for a+b functions
val sum: (Int, Int) -> Int = { a,b -> a+b }

// generated functions
fun add1(x:Int) = sum(x,1)
fun add2(x:Int) = sum(x,2)
fun add3(x:Int) = sum(x,3)
// same but as a val from anonymous function
val add4: (Int) -> Int = fun (x:Int) = sum(x,4)
val add5: (Int) -> Int = { sum(it,5) }

// first unit to bind
// so-called 'pure'
fun unit(x: Int) = x to "Eval:"

val foo: () -> Unit = {  }
// binds previous and current function
fun bind(prev: Pair<Int, String>, f: (Int) -> Int) = run {
    val res = f(prev.first)
    res to prev.second+ "${prev.first}+${res-prev.first};"
}

// pipeline is an overlay for binds
fun pipeline(unit: Pair<Int, String>, vararg functions: (Int) -> Int) = run {
    var eval = unit
    functions.forEach {
        eval = bind(eval, it)
    }
    eval
}

// testing
fun main(args: Array<String>) {
    when {}
    // why not make a WHOLE ARRAY of functions?
    val functions = (1..1000).map {
        fun (x:Int) = sum(x, it)
    }.toTypedArray()
    // running a pipeline
    println(pipeline(unit(0), *functions).let { "${it.first}\n${it.second}"})
}
