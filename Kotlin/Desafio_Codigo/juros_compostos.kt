import java.text.DecimalFormat

fun main() {
    val valorInicial = 20000
    val taxaJuros = 0.04
    val periodo = 10
    var valorFinal = valorInicial.toDouble()
    val df = DecimalFormat("0.00")
    for (ano in 1..periodo) {
        valorFinal += valorFinal * taxaJuros
    }
    println("Valor final do investimento: R$ ${df.format(valorFinal)}")
}