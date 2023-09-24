
fun main() {
    for(i in 0..3) {
        print(i)
    }
    print(" ")

    for(i in 0 until 3) {
        print(i)
    }
    print(" ")

    for(i in 2..8 step 2) {
        print(i)
    }
    print(" ")

    for(i in 2 until 8 step 2) {
        print(i)
    }
    print(" ")

    for(i in 3 downTo 0) {
        print(i)
    }
    print(" ")

    for(i in 5 downTo 0 step 2) {
        print(i)
    }
    print(" ")
    val x = 3
    if (x in 1..5) {
        print("x is in range from 1 to 5")
    }
}