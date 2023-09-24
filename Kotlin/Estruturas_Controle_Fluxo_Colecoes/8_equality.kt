fun main() {
    val authors = setOf("Shakespeare", "Hemingway", "Twain")
    val writers = setOf("Twain", "Shakespeare", "Hemingway")
    val writers2 = writers
    val writers3 = setOf("Twain", "Shakespeare", "Hemingway")
    println(authors == writers) // Returns true because it calls authors.equals(writers) and sets ignore element order.
    println(writers === authors) // Returns false because authors and writers are distinct references.
    println(writers2 === writers) // true
    println(writers3 === writers) // false
}