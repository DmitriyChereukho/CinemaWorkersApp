package domain

import data.FilmDao

interface FilmController {
    fun addFilm(title: String): String

    fun removeFilm(title: String): String

    fun getFilmCount(): Int

    fun getAllToString(): String
}

class FilmControllerImpl(private val filmDao: FilmDao) : FilmController {
    override fun addFilm(title: String): String {
        if(filmDao.containsValue(title)){
            return "Фильм с таким названием уже есть в прокате"
        }
        filmDao.add(title)
        return "Фильм добавлен в прокат"
    }

    override fun removeFilm(title: String): String {
        if(!filmDao.containsValue(title)){
            return "Фильма с таким названием нет в прокате"
        }
        filmDao.remove(title)
        return "Фильм убран из проката"
    }

    override fun getFilmCount(): Int {
        return filmDao.getSize()
    }

    override fun getAllToString(): String {
        return filmDao.getAll().joinToString("\n")
    }

}