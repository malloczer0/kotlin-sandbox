import java.lang.annotation.Inherited
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

// annotation
@Inherited
@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.EXPRESSION
)
annotation class Foo(
    val fooString: String = ""
)

const val blipBlapBlop = "blip-blap-blop"
@Foo
open class Parent {
    @Foo(blipBlapBlop)
    open fun someMethod(fooString: String): String = run {
        println(this.hashCode())
        "some stuff $fooString".also(::println)
    }
}

class Child: Parent() {
    override fun someMethod(fooString: String) = super.someMethod(fooString)

    fun someMoreMethod() = "some more stuff".also(::println)
}

// 1 or more args in the middle
fun Child.overlay1(f: KFunction2<Child, String, String>) = f(this, Parent::class.hashCode().toString())
// no args but class
fun Child.overlay2(f: KFunction1<Child, String>) = f(this)

fun main() {
    Child().also { it.overlay1(Child::someMethod) }.also { it.overlay2(Child::someMoreMethod) }
}