package di

import data.FilmDao
import data.RuntimeFilmDao
import data.RuntimeSessionDao
import data.SessionDao
import domain.FilmController
import domain.FilmControllerImpl
import domain.SessionController
import domain.SessionControllerImpl


object DI {
    private val filmDao: FilmDao by lazy {
        RuntimeFilmDao()
    }

    private val sessionDao: SessionDao by lazy {
        RuntimeSessionDao()
    }

    val filmController: FilmController
        get() = FilmControllerImpl(filmDao = filmDao)

    val sessionController: SessionController
        get() = SessionControllerImpl(sessionDao = sessionDao, filmDao = filmDao)

}