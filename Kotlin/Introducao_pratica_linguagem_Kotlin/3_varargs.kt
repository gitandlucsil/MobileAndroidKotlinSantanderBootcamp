fun printAll(vararg messages: String) {
    for (m in messages) print("$m, ")
    println("")
}

fun printAllWithPrefix(vararg messages: String, prefix: String) {
    for (m in messages) print("$prefix $m, ")
    println("")
}

fun Log(vararg entries: String) {
    printAll(*entries)
}


fun main() {
    printAll("Hello", "Hallo", "Hola", "Ola")
    printAll("Mundo", "World")
    printAllWithPrefix("Hello", "Hallo", "Hola", "Ola", prefix = "Greeting: ")
    Log("Hello", "Hallo", "Hola", "Ola")
}