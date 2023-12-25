package presentation

import kotlinx.datetime.LocalDateTime

interface Reader {
    fun readNatural(lowerBound: Int, upperBound: Int): Int

    fun readDate(): LocalDateTime

    fun readNotNullString(): String
}

class ReaderImpl : Reader {
    override fun readNatural(lowerBound: Int, upperBound: Int): Int {
        while (true) {
            try {
                val input = readlnOrNull()?.toInt()
                if (input != null) {
                    if (input < lowerBound || input > upperBound) {
                        throw Exception()
                    }
                    return input
                }
            } catch (ex: Exception) {
                println("Ошибка: введите корректное натуральное число")
            }
        }
    }

    private fun parseDate(input: String?): LocalDateTime {
        if (input == null) {
            throw IllegalArgumentException()
        }
        val parsedBySpace = input.split(' ')
        if (parsedBySpace.size != 2) {
            throw IllegalArgumentException()
        }
        val parsedDate = parsedBySpace[0].split('.')
        if (parsedDate.size != 2) {
            throw IllegalArgumentException()
        }
        val parsedTime = parsedBySpace[1].split(':')
        if (parsedTime.size != 2) {
            throw IllegalArgumentException()
        }
        return LocalDateTime(
            2023,
            parsedDate[1].toInt(),
            parsedDate[0].toInt(),
            parsedTime[0].toInt(),
            parsedTime[1].toInt(),
            0
        )
    }

    override fun readDate(): LocalDateTime {
        var dateTime: LocalDateTime?
        while (true) {
            try {
                dateTime = parseDate(readlnOrNull())
            } catch (e: IllegalArgumentException) {
                println("Некорректный формат даты и времени. Попробуйте снова.")
                continue
            }
            return dateTime
        }

    }

    override fun readNotNullString(): String {
        while (true) {
            val input = readlnOrNull()
            if (input == null) {
                println("Введите корректное название фильма")
                continue
            }
            return input
        }

    }


}