var a: String = "initial" //mutable variable
val b: Int = 1 //immutable value
val c = 3 //immutable value with type not infered (compiler assumes Int)
var d = 34
var e = "new"

fun someCondition() = true

fun main() {
    println("$a $b $c")
    a = "changed"
    println("$a $b $c")
    println("$d $e")
    d = 45
    println("$d $e")
    e = "dog"
    println("$d $e")
    val f: Int
    if (someCondition()) {
        f = 1
    } else {
        f = 2
    }
    println(f)
}