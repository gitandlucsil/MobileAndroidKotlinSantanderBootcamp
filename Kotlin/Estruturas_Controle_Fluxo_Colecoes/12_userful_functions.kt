fun main() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val positives = numbers.filter {x -> x > 0}
    val negatives = numbers.filter {it < 0}

    println("Numbers: $numbers")
    println("Positive numbers: $positives")
    println("Negative numbers: $negatives")

    val doubled = numbers.map {x -> x * 2}
    val tripled = numbers.map {it * 3}

    println("Doubled numbers: $doubled")
    println("Tripled numbers: $tripled")
}