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
    fun update(vararg listFilms: FilmEntity)
    fun updateJson()
}

class RuntimeFilmDao : FilmDao {
    private val films =
        Json.decodeFromString<MutableMap<Int, FilmEntity>>(File("src/main/resources/filmsJson.json").readText())

    private var counter = films.keys.max() + 1
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

    override fun update(vararg listFilms: FilmEntity) {
        listFilms.forEach { film ->
            films[film.id] = film
        }
    }

    override fun updateJson() {
        val file = File("src/main/resources/filmsJson.json")
        file.writeText(Json.encodeToString(films))
    }

}