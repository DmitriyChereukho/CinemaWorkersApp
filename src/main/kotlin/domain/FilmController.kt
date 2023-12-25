package domain

import data.FilmDao
import domain.entity.FilmEntity

interface FilmController {
    fun addFilm(title: String): String

    fun removeFilm(title: String): String

    fun getAll(): List<FilmEntity>

    fun getAllToString(): String
}

class FilmControllerImpl(private val filmDao: FilmDao) : FilmController {
    override fun addFilm(title: String): String {
        filmDao.add(title)
        return "film added"
    }

    override fun removeFilm(title: String): String {
        filmDao.remove(title)
        return "film removed"
    }

    override fun getAll(): List<FilmEntity> {
        return filmDao.getAll()
    }

    override fun getAllToString(): String {
        return getAll().joinToString("\n")
    }

}