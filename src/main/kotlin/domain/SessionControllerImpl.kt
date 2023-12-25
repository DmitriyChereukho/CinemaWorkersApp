package domain

import data.FilmDao
import data.SessionDao
import domain.entity.SessionEntity
import kotlinx.datetime.LocalDateTime

class SessionControllerImpl(private val sessionDao: SessionDao, private val filmDao: FilmDao) : SessionController {

    override fun addSession(filmId: Int, time: LocalDateTime): String {
        val film = filmDao.get(filmId) ?: return "В прокате нет фильма с таким id"
        sessionDao.add(film, time)
        return "Сеанс добавлен в расписание"
    }

    override fun removeSession(sessionId: Int): String {
        if (!sessionDao.containsValue(sessionId)) {
            return "В расписании нет сеанса с таким id"
        }
        sessionDao.remove(sessionId)
        return "Сеанс убран из расписания"
    }

    override fun sellTicket(sessionId: Int, rowNumber: Int, seatNumber: Int): String {
        val session = sessionDao.get(sessionId) ?: return "В расписании нет сеанса с таким id"
        if (session.cinemaHall.seatsInHall[rowNumber - 1][seatNumber - 1].seatState != 0) {
            return "Билет на это место уже куплен"
        }
        if (session.cinemaHall.availableSeatsNum == 0) {
            return "Все места на сеансе куплены"
        }
        session.cinemaHall.availableSeatsNum--
        session.cinemaHall.sellSeat(rowNumber, seatNumber)
        return "Продажа билета зафиксирована"
    }

    override fun returnTicket(sessionId: Int, rowNumber: Int, seatNumber: Int): String {
        val session = sessionDao.get(sessionId) ?: return "В расписании нет сеанса с таким id"
        if (session.cinemaHall.seatsInHall[rowNumber - 1][seatNumber - 1].seatState == 0) {
            return "Билет на это место не куплен"
        }
        if (session.cinemaHall.seatsInHall[rowNumber - 1][seatNumber - 1].seatState == 2) {
            return "Место по билету уже занято"
        }
        session.cinemaHall.releaseSeat(rowNumber, seatNumber)
        return "Возврат билета зафиксирован"
    }

    override fun takeSeat(sessionId: Int, rowNumber: Int, seatNumber: Int): String {
        val session = sessionDao.get(sessionId) ?: return "В расписании нет сеанса с таким id"
        if (session.cinemaHall.seatsInHall[rowNumber - 1][seatNumber - 1].seatState == 2) {
            return "Место уже занято"
        }
        if (session.cinemaHall.seatsInHall[rowNumber - 1][seatNumber - 1].seatState == 0) {
            return "Билет на это место не куплен"
        }
        session.cinemaHall.takeSeat(rowNumber, seatNumber)
        return "Занятое место зафиксировано"
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

    override fun getSessionCount(): Int {
        return sessionDao.getSize()
    }

}