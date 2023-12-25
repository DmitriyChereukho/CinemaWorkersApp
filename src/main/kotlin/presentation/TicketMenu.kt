package presentation

import di.DI
import kotlin.Int
import kotlin.Int as Int1

interface TicketMenu {
    fun readSeat(): Pair<Int, Int>
}

class TicketMenuImpl : TicketMenu {
    override fun readSeat(): Pair<Int, Int> {
        println("Выберите номер ряда (от 1 до 5):")
        val rowNumber = DI.reader.readNatural(1, 5)
        println("Выберите номер места (от 1 до 10):")
        val seatNumber = DI.reader.readNatural(1, 10)
        return Pair(rowNumber, seatNumber)
    }

}