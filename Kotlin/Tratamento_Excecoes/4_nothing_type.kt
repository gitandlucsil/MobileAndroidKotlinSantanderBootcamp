data class Person(val name: String?)

fun fail(message: String): Nothing{
    throw IllegalArgumentException(message)
}

fun main() {
    //val person = Person("João")
    val person = Person(null)
    val s = person.name ?: fail("Name required")
    println(s)
}