open class Dog {
    open fun sayHello() {
        println("wow wow!")
    }
}

class Yorkshire : Dog() {
    override fun sayHello() {
        println("wif wif")
    }
}

class Bulldog : Dog() {

}

fun main() {
    val dog : Dog = Yorkshire()
    dog.sayHello()
    val newDog : Dog = Bulldog()
    newDog.sayHello()
    val babyDog : Dog = Dog()
    babyDog.sayHello()
}