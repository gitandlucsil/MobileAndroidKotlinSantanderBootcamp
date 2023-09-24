data class Voter (val name: String, val age: Int)

class IllegalVoterException(message: String) : Throwable(message)

@Throws(IllegalVoterException::class)
fun vote(voter: Voter) {
    if (voter.age < 16) {
        throw IllegalVoterException("${voter.name} não pode votar. Apenas pessoas com 16 anos ou mais podem votar!")
    }
    println("Voto de ${voter.name} realizado com sucesso!")
}



fun main() {
    try {
        vote(Voter("Venilton", 33))
        vote(Voter("Carlos", 15))
        vote(Voter("Renan", 50))
    } catch (e: IllegalVoterException) {
        e.printStackTrace()
    }

}