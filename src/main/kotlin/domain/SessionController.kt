package domain

import data.FilmDao
import data.SessionDao
import domain.entity.SessionEntity

interface SessionController {
    fun addSession(filmId: Int, time: Int)

    fun removeSession(sessionId: Int)

    fun sellTicket(sessionId: Int, rowNumber: Int, seatNumber: Int)

    fun returnTicket(sessionId: Int, rowNumber: Int, seatNumber: Int)

    fun takeSeat(sessionId: Int, rowNumber: Int, seatNumber: Int)

    fun getCinemaHallInfo(sessionId: Int): String

    fun getAllToString(): String
}

class SessionControllerImpl(private val sessionDao: SessionDao, private val filmDao: FilmDao) : SessionController {

    override fun addSession(filmId: Int, time: Int) {
        val film = filmDao.get(filmId)
        if (film != null) {
            sessionDao.add(film, time)
        }
    }

    override fun removeSession(sessionId: Int) {
        sessionDao.remove(sessionId)
    }

    override fun sellTicket(sessionId: Int, rowNumber: Int, seatNumber: Int) {
        val session = sessionDao.get(sessionId)
        if (session != null) {
            if (session.cinemaHall.availableSeatsNum > 0) {
                session.cinemaHall.availableSeatsNum--
                session.cinemaHall.sellSeat(rowNumber, seatNumber)
            }
        }
    }

    override fun returnTicket(sessionId: Int, rowNumber: Int, seatNumber: Int) {
        val session = sessionDao.get(sessionId)
        if (session != null) {
            if (session.cinemaHall.availableSeatsNum > 0) {
                session.cinemaHall.availableSeatsNum--
                session.cinemaHall.releaseSeat(rowNumber, seatNumber)
            }
        }
    }

    override fun takeSeat(sessionId: Int, rowNumber: Int, seatNumber: Int) {
        val session = sessionDao.get(sessionId)
        session?.cinemaHall?.takeSeat(rowNumber, seatNumber)
    }

    override fun getCinemaHallInfo(sessionId: Int): String {
        return sessionDao.get(sessionId)?.cinemaHall.toString()
    }

    private fun getAllSessions(): List<SessionEntity> {
        return sessionDao.getAll()
    }

    override fun getAllToString(): String {
        return getAllSessions().joinToString("\n")
    }
}