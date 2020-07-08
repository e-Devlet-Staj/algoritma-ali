class NiceString {

    fun min3Vowel(s: String): Int {
        var count = 0
        for(i in 0..s.length){
            if(s.get(i)=='a' || s.get(i)=='e' || s.get(i)=='i' ||
                    s.get(i)=='o' || s.get(i)=='u'){
                count++
            }
            if(count == 3) return 1
        }

        return 0
    }

    fun doubleLetter(s: String): Int{
        for (i in 0 ..s.length) {
            try {
                if (s[i] === s.get(i + 1)) {
                    return 1
                }
            } catch (e: StringIndexOutOfBoundsException) {
            }
        }
        return 0
    }

    fun doesntContainBubabe(s: String): Int {
        for (i in 0 until s.length) {
            try {
                if (s[i] == 'b' && (s[i + 1] == 'u' || s[i + 1] == 'a' || s[i + 1] == 'e')) {
                    return 0
                }
            } catch (e: StringIndexOutOfBoundsException) {
            }
        }
        return 1
    }

}