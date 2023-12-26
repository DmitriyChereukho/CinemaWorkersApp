package presentation

import di.DI

interface SessionMenu {
    fun processMenu()

    fun readSessionId(): Int?
}

class SessionMenuImpl : SessionMenu {

    private fun printMenu() {
        println("Текущее расписание:")
        println(DI.sessionController.getAllToString())
        println("1. Добавить сеанс")
        println("2. Удалить сеанс")
        println("3. Назад")
    }

    override fun processMenu() {
        while (true) {
            printMenu()
            when (DI.reader.readNatural(1, 3)) {
                1 -> {
                    println("Текущий прокат:")
                    println(DI.filmController.getAllToString())
                    println("Выберите фильм по id:")
                    val filmId = DI.reader.readNatural(0, DI.filmController.getFilmCount())
                    print("Введите день.месяц часы:минуты в формате 'dd.mm hh:mm':")
                    val dateTime = DI.reader.readDate()
                    println(DI.sessionController.addSession(filmId, dateTime))
                }

                2 -> {
                    println("Выберите сеанс по id:")
                    val sessionId = DI.reader.readNatural(0, DI.sessionController.getSessionCount())
                    println(DI.sessionController.removeSession(sessionId))
                }

                3 -> {
                    break
                }
            }
        }
    }

    override fun readSessionId(): Int? {
        println("Текущее расписание:")
        println(DI.sessionController.getAllToString())
        println("Выберите сеанс по id:")
        val sessionId = DI.reader.readNatural(0, DI.sessionController.getSessionCount())
        if (!DI.sessionController.sessionExist(sessionId)) {
            return null
        }
        println(DI.sessionController.getCinemaHallInfo(sessionId))
        println("0 - место не куплено")
        println("1 - место куплено")
        println("2 - место занято посетителем\n")
        return sessionId
    }

}