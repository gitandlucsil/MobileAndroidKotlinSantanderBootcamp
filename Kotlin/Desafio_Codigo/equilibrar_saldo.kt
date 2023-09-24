import java.text.DecimalFormat

fun main() {
    //val saldoAtual = readLine()!!.toFloat()
    //val valorDeposito = readLine()!!.toFloat()
    //val valorRetirada = readLine()!!.toFloat()
    val saldoAtual = 4000.0
    val valorDeposito = 1500.0
    val valorRetirada = 200.0

    print("Saldo atuaizado na conta: ${saldoAtual+valorDeposito-valorRetirada}")
}