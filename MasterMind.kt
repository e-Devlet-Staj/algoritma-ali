import java.util.*

class MasterMind {
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        println("Enter the secret code:")
        val secretCode: String = input.nextLine()
        var attempt = ""
        var positions = 0
        var letters = 0
        var temp = ' '
        var attemptArray = CharArray(secretCode.length)
        while (true) {
            System.out.printf("(Code length is %d)\nEnter your guess:       ---Press 'x' to give up--\n", secretCode.length)
            positions = 0
            letters = 0
            attempt = input.nextLine()
            if (attempt == "x") {
                println("Game is closed")
                return
            }
            if (attempt.length != secretCode.length) {
                println("The given code's length is different than secret code.")
                continue
            }
            for (i in 0..secretCode.length) {
                if (secretCode[i] == attempt[i]) {
                    positions++
                }
            }
            attemptArray = attempt.toCharArray()
            //attemptimizde secretCode daki harflerden herhangi birini görünce(index önemsenmeksizin)
            //attempin o charını 0 a ceviriyoruz ki daha sonraki aramalarda tekrardan saymayalım
            for (i in 0 until secretCode.length) {
                temp = secretCode[i]
                for (j in 0 until attempt.length) {
                    if (attemptArray[j] == temp) {
                        attemptArray[j] = '0'
                        letters++
                        break
                    }
                }
            }
            //yukardaki letters sayma metodu aynı indexte olanları da dahil ettiği için
            //positions ı cıkarmamız lazım
            letters -= positions
            System.out.printf("Positions: %d\n", positions)
            System.out.printf("Letters: %d\n", letters)
            println("|||||||||||||||||||||||||||||||||||||")
            if (positions == secretCode.length) {
                println("Congrats you guessed the secret code correctly :)")
                return
            }
        }
    }
}