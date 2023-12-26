package data

import domain.entity.CinemaHall
import domain.entity.FilmEntity
import domain.entity.SessionEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlinx.datetime.LocalDateTime

interface SessionDao {
    fun add(filmEntity: FilmEntity, time: LocalDateTime)
    fun remove(sessionId: Int)
    fun getAll(): List<SessionEntity>
    fun get(id: Int): SessionEntity?
    fun getSize(): Int
    fun containsValue(id: Int): Boolean
    fun removeAllTitles(title: String)
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

    override fun add(filmEntity: FilmEntity, time: LocalDateTime) {
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
        return counter - 1
    }

    override fun containsValue(id: Int): Boolean {
        sessions.forEach { (_, value) ->
            if (value.id == id) {
                return true
            }
        }
        return false
    }

    override fun removeAllTitles(title: String) {
        sessions.entries.removeIf { it.value.filmEntity.title == title }
    }

    private fun updateJson() {
        val file = File("src/main/resources/sessionsJson.json")
        file.writeText(Json.encodeToString(sessions))
    }


}