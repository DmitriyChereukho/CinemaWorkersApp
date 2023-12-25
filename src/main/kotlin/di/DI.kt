package di

import data.*
import domain.*
import presentation.*


object DI {
    val reader: Reader
        get() = ReaderImpl()

    val filmMenu: FilmMenu
        get() = FilmMenuImpl()

    val ticketMenu: TicketMenu
        get() = TicketMenuImpl()

    val sessionMenu: SessionMenu
        get() = SessionMenuImpl()

    val loginMenu: LoginMenu
        get() = LoginMenuImpl()

    private val loginDao: LoginDao by lazy {
        RuntimeLoginDao()
    }

    private val filmDao: FilmDao by lazy {
        RuntimeFilmDao()
    }

    private val sessionDao: SessionDao by lazy {
        RuntimeSessionDao()
    }

    val loginController: LoginController
        get() = LoginControllerImpl(loginDao = loginDao)

    val filmController: FilmController
        get() = FilmControllerImpl(filmDao = filmDao, sessionDao = sessionDao)

    val sessionController: SessionController
        get() = SessionControllerImpl(sessionDao = sessionDao, filmDao = filmDao)

}