import java.text.DecimalFormat

fun main() {
    val valor = 500.50

    val df = DecimalFormat("0.00")
    if (valor > 0) {
        println("Deposito realizado com sucesso!")
        print("Saldo atual: R$ ${df.format(valor)}")
    }  else if (valor == 0.0) {
        print("Encerrando o programa...")
    } else {
        print("Valor invalido! Digite um valor maior que zero.")
    }
}