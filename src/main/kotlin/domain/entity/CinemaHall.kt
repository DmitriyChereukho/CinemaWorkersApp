package domain.entity

const val ROWS = 5
const val COLS = 10

class CinemaHall {
    var availableSeatsNum: Int = ROWS * COLS
    private val seatsInHall = Array(ROWS) { row ->
        Array(COLS) { col ->
            SeatEntity(row, col, 0)
        }
    }

    fun sellSeat(rowNumber: Int, seatNumber: Int) {
        seatsInHall[rowNumber - 1][seatNumber - 1].seatState = 1
    }

    fun releaseSeat(rowNumber: Int, seatNumber: Int) {
        seatsInHall[rowNumber - 1][seatNumber - 1].seatState = 0
    }

    fun takeSeat(rowNumber: Int, seatNumber: Int){
        seatsInHall[rowNumber - 1][seatNumber - 1].seatState = 2
    }

    override fun toString(): String {
        var cinemaHallToString = ""
        var rowNumber = 1
        for (row in seatsInHall) {
            cinemaHallToString += "$rowNumber ряд: "
            rowNumber++
            for (element in row) {
                cinemaHallToString += "${element.seatState} "
            }
            cinemaHallToString += "\n"
        }
        return cinemaHallToString
    }
}