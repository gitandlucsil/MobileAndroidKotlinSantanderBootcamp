fun main() {
    val saldoTotal = 300
    val valorSaque = 200
    //Saque so pode ser realizado se o saldoDisponivel na conta for igual ou superior ou valor solicitado
    if (valorSaque > saldoTotal) {
        println("Saldo insuficiente. Saque nao realizado!")
    } else {
        println("Saque realizado com sucesso. Novo saldo: ${saldoTotal - valorSaque}")
    }
}