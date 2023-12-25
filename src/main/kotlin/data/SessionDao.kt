package data

import domain.entity.CinemaHall
import domain.entity.FilmEntity
import domain.entity.SessionEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

interface SessionDao {
    fun add(filmEntity: FilmEntity, time: Int)
    fun remove(sessionId: Int)
    fun getAll(): List<SessionEntity>
    fun get(id: Int): SessionEntity?
    fun getSize(): Int
}

class RuntimeSessionDao : SessionDao {
    private val sessions: MutableMap<Int, SessionEntity> = try {
        Json.decodeFromString<MutableMap<Int, SessionEntity>>(File("src/main/resources/sessionsJson.json").readText())
    } catch (e: Exception) {
        mutableMapOf()
    }
    private var counter = if (sessions.isNotEmpty()) {
        sessions.keys.max() + 1
    } else {
        0
    }

    override fun add(filmEntity: FilmEntity, time: Int) {
        sessions[counter] = SessionEntity(filmEntity, time, CinemaHall(), counter)
        counter++

        updateJson()
    }

    override fun remove(sessionId: Int) {
        sessions.entries.removeIf { it.value.id == sessionId }

        updateJson()
    }

    override fun getAll(): List<SessionEntity> {
        return sessions.values.toList()
    }

    override fun get(id: Int): SessionEntity? {
        return sessions[id]
    }

    override fun getSize(): Int {
        return sessions.size
    }

    private fun updateJson() {
        val file = File("src/main/resources/filmsJson.json")
        file.writeText(Json.encodeToString(sessions))
    }


}