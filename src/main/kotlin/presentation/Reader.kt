package presentation

interface Reader {
    fun readNatural(upperBound: Int): Int
}

class ReaderImpl : Reader {
    override fun readNatural(upperBound: Int): Int {
        while (true) {
            try {
                val input = readlnOrNull()?.toInt()
                if (input != null) {
                    if (input < 1 || input > upperBound) {
                        throw Exception()
                    }
                    return input
                }
            } catch (ex: Exception) {
                println("Ошибка: введите корректное натуральное число")
            }
        }
    }

}