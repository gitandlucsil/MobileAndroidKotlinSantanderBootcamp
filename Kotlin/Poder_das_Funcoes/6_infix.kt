fun main() {
    infix fun Int.times(str: String) = str.repeat(this)
    println(2 times " Bye ")

    infix fun String.get(range: IntRange) = substring(range)
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str get(0..14))

    infix fun Int.subtract(x: Int) = this - x
    println(5 subtract 4)

    val pair = "Ferrari" to "Katrina"
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)   
    val myPair = "McLaren" onto "Lucas"
    println(myPair)
  
    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia                                      
  }
  
  class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }  
  }