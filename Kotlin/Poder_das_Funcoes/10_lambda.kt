fun main() {

    val sum1: (Int, Int) -> Int = {x: Int, y: Int -> x + y}
    val sum2: (Int, Int) -> Int = {x, y -> x + y}
    val sum3 = {x: Int, y: Int -> x + y}

    println(sum1(4, 5))
    println(sum2(4, 5))
    println(sum3(4, 5))
}