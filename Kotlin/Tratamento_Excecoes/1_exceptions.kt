fun main() {
    val a = 10
    val b = 0
    try {
        val divisao = a/b
    } catch (e: ArithmeticException) {
        println("Ocorreu uma exceção aritmética.")
    } catch (e: Throwable) {
        //e.printStackTrace();
        println(e.message)
    } finally {
        println("Finally executado!")
    } 
}