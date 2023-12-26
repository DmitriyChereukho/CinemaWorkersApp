package data

import domain.entity.FilmEntity

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


interface FilmDao {
    fun add(title: String)
    fun remove(title: String)
    fun getAll(): List<FilmEntity>
    fun get(id: Int): FilmEntity?
    fun getSize(): Int
    fun containsValue(title: String): Boolean
}

class RuntimeFilmDao : FilmDao {
    private val films =
        try {
            Json.decodeFromString<MutableMap<Int, FilmEntity>>(File("src/main/resources/filmsJson.json").readText())
        } catch (e: Exception) {
            mutableMapOf()
        }

    private var counter = if (films.isNotEmpty()) {
        films.keys.max() + 1
    } else {
        0
    }

    override fun add(title: String) {
        val film = FilmEntity(title, counter)
        films[counter] = film
        counter++
        updateJson()
    }

    override fun remove(title: String) {
        films.entries.removeIf { it.value.title == title }
        updateJson()
    }

    override fun getAll(): List<FilmEntity> {
        return films.values.toList()
    }

    override fun get(id: Int): FilmEntity? {
        return films[id]
    }

    private fun updateJson() {
        val file = File("src/main/resources/filmsJson.json")
        file.writeText(Json.encodeToString(films))
    }

    override fun getSize(): Int {
        return counter - 1
    }

    override fun containsValue(title: String): Boolean {
        films.forEach { (_, value) ->
            if (value.title == title) {
                return true
            }
        }
        return false
    }

}