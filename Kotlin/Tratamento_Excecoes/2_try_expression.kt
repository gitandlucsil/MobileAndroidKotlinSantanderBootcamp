fun main() {
    val a = 10
    val b = 0

    val divisao: String? = try { 
        a/b
     } catch (e: ArithmeticException) { 
        "Divisor inválido!" 
    }
    println(divisao)
}