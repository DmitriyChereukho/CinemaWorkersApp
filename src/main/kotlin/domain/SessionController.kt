package domain

import kotlinx.datetime.LocalDateTime

interface SessionController {
    fun addSession(filmId: Int, time: LocalDateTime): String

    fun removeSession(sessionId: Int): String

    fun sellTicket(sessionId: Int, rowNumber: Int, seatNumber: Int): String

    fun returnTicket(sessionId: Int, rowNumber: Int, seatNumber: Int): String

    fun takeSeat(sessionId: Int, rowNumber: Int, seatNumber: Int): String

    fun sessionExist(sessionId: Int): Boolean

    fun getCinemaHallInfo(sessionId: Int): String

    fun getAllToString(): String

    fun getSessionCount(): Int
}
