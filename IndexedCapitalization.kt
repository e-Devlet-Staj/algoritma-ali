class IndexedCapitalization

fun indexedCapitalization(string: String, indexes: IntArray): String? {
    var charArray = string.toCharArray()
    val result = ""
    for (i in indexes.indices) {
        try {
            charArray[indexes[i] - 1] = Character.toUpperCase(charArray[indexes[i] - 1])
        } catch (e: Exception) {}
    }
    return String(charArray)
}