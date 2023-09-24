fun main() {

    val ativos = mutableListOf<String>("F", "D", "R")
    ativos.sort()
    for (ativo in ativos) {
        println(ativo)
    }
}