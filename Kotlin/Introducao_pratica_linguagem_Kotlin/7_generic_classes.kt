class MutableStack<E>(vararg items: E) {
    private val elements = items.toMutableList()
    fun push(element: E) = elements.add(element)
    fun peek(): E = elements.last()
    fun pop(): E = elements.removeAt(elements.size - 1)
    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size
    override fun toString() = "MutableStack(${elements.joinToString()})"
}

fun main() {
    val stack = MutableStack(0.62, 3.14, 2.7)
    println(stack)
    stack.push(9.87)
    println(stack)
    println("peek(): ${stack.peek()}")
    println("isEmpty(): ${stack.isEmpty()}")
    println("size(): ${stack.size()}")
    for (i in 1..stack.size()) {
        println("pop(): ${stack.pop()}")
        println(stack)
    }
    println("isEmpty(): ${stack.isEmpty()}")
    //
    val stackString = MutableStack("Dog", "Cat", "Bird")
    println(stackString)
}