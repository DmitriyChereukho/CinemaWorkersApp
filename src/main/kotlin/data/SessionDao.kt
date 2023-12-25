package data

import domain.entity.CinemaHall
import domain.entity.FilmEntity
import domain.entity.SessionEntity

interface SessionDao {
    fun add(filmEntity: FilmEntity, time: Int)
    fun remove(sessionId: Int)
    fun getAll(): List<SessionEntity>
    fun get(id: Int): SessionEntity?
    fun update(vararg listFilms: SessionEntity)
}

class RuntimeSessionDao : SessionDao {
    private val sessions = mutableMapOf<Int, SessionEntity>()
    private var counter = 0
    override fun add(filmEntity: FilmEntity, time: Int) {
        sessions[counter] = SessionEntity(filmEntity, time, CinemaHall(), counter)
        counter++
    }

    override fun remove(sessionId: Int) {
        sessions.entries.removeIf {it.value.id == sessionId}
    }

    override fun getAll(): List<SessionEntity> {
        return sessions.values.toList()
    }

    override fun get(id: Int): SessionEntity? {
        return sessions[id]
    }

    override fun update(vararg listFilms: SessionEntity) {
        TODO("Not yet implemented")
    }

}