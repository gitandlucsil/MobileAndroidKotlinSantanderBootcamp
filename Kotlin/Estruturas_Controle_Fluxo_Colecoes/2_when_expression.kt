fun cases(obj: Any): Any {
    val result = when (obj) {
        1 -> "one"
        "Hello" -> 1
        is Long -> false
        !is String -> "Not a string"
        else -> 42
    }
    return result
}

class MyClass

fun main() {
    println(cases("Hello"))
    println(cases(1))
    println(cases(0L))
    println(cases(MyClass()))
    println(cases("hello"))
}
