package presentation

import di.DI


interface FilmMenu {
    fun processMenu()

}

class FilmMenuImpl : FilmMenu {
    private fun printMenu() {
        println("Текущий прокат:")
        println(DI.filmController.getAllToString())
        println("1. Добавить фильм")
        println("2. Удалить фильм")
        println("3. Назад")
    }

    override fun processMenu() {
        while (true) {
            printMenu()
            when (DI.reader.readNatural(1, 3)) {
                1 -> {
                    println("Введите название фильма:")
                    println(DI.filmController.addFilm(DI.reader.readNotNullString()))
                }

                2 -> {
                    println("Введите название фильма:")
                    println(DI.filmController.removeFilm(DI.reader.readNotNullString()))
                }

                3 -> {
                    break
                }
            }
        }
    }

}