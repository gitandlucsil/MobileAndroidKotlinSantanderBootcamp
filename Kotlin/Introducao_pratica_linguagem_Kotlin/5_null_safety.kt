fun strLength(notNull: String): Int {
    return notNull.length
}

fun strLengthNull(notNull: String?): Int {
    return notNull?.length ?: 0 
}

fun describeString(maybeString: String?): String {
    if (maybeString != null && maybeString.length > 0) {
        return "String of length ${maybeString.length}"
    } else {
        return "Empty or null string"
    }
}

fun main() {
    var neverNull: String = "This can't be null"
    //neverNull = null // This launchs an exception
    var nullable: String? = "You can keep a null here"
    nullable = null
    var inferredNonNull = "The compiler assumes non-null"
    //inferredNonNull = null // This launchs an exception
    println(strLength(neverNull))
    //println(strLength(nullable)) // This launchs an exception
    println(strLengthNull(nullable))
    println(describeString(null))
    println(describeString(""))
    println(describeString("me"))
}