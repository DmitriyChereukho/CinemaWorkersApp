package data

import domain.entity.FilmEntity

interface CinemaDao {
    fun add(title: String)
    fun remove(title: String)
    fun getAll(): List<FilmEntity>
    fun get(id: Int): FilmEntity?
    fun update(vararg listFilms: FilmEntity)
    fun updateJson()
    fun getSize(): Int
}