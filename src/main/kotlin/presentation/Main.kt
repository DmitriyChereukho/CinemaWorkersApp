package presentation

import di.DI

fun main() {
    while (true) {
        println("1. Посмотреть текущий прокат")
        println("2. Посмотреть расписание сеансов")
        println("3. Продать билет")
        println("4. Возврат билета")
        println("5. Отметить занятое место на ближайшем сеансе")
        println("6. Выход")

        val choice0 = DI.reader.readNatural(1, 6)
        when (choice0) {
            1 -> {
                while (true) {
                    println("Текущий прокат:")
                    println(DI.filmController.getAllToString())
                    println("1. Добавить фильм")
                    println("2. Удалить фильм")
                    println("3. Назад")
                    val choice1 = DI.reader.readNatural(1, 3)
                    when (choice1) {
                        1 -> {
                            println("Введите название фильма:")
                            println(DI.filmController.addFilm(DI.reader.readFilmTitle()))
                        }

                        2 -> {
                            println("Введите название фильма:")
                            println(DI.filmController.removeFilm(DI.reader.readFilmTitle()))
                        }

                        3 -> {
                            break
                        }
                    }
                }
            }

            2 -> {
                while (true) {
                    println("Текущее расписание:")
                    println(DI.sessionController.getAllToString())
                    println("1. Добавить сеанс")
                    println("2. Удалить сеанс")
                    println("3. Назад")
                    val choice2 = DI.reader.readNatural(1, 3)
                    when (choice2) {
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

            3 -> {
                println("Текущее расписание:")
                println(DI.sessionController.getAllToString())
                println("Выберите сеанс по id:")
                val sessionId = DI.reader.readNatural(0, DI.sessionController.getSessionCount())
                println(DI.sessionController.getCinemaHallInfo(sessionId))

                println("Выберите номер ряда (от 1 до 5):")
                val rowNumber = DI.reader.readNatural(1, 5)
                println("Выберите номер места (от 1 до 10):")
                val seatNumber = DI.reader.readNatural(1, 10)
                println(DI.sessionController.sellTicket(sessionId, rowNumber, seatNumber))
            }

            4 -> {
                println("Текущее расписание:")
                println(DI.sessionController.getAllToString())
                println("Выберите сеанс по id:")
                val sessionId = DI.reader.readNatural(0, DI.sessionController.getSessionCount())
                println(DI.sessionController.getCinemaHallInfo(sessionId))

                println("Выберите номер ряда (от 1 до 5):")
                val rowNumber = DI.reader.readNatural(1, 5)
                println("Выберите номер места (от 1 до 10):")
                val seatNumber = DI.reader.readNatural(1, 10)
                println(DI.sessionController.returnTicket(sessionId, rowNumber, seatNumber))
            }

            5 -> {
                println("Текущее расписание:")
                println(DI.sessionController.getAllToString())
                println("Выберите сеанс по id:")
                val sessionId = DI.reader.readNatural(0, DI.sessionController.getSessionCount())
                println(DI.sessionController.getCinemaHallInfo(sessionId))

                println("Выберите номер ряда (от 1 до 5):")
                val rowNumber = DI.reader.readNatural(1, 5)
                println("Выберите номер места (от 1 до 10):")
                val seatNumber = DI.reader.readNatural(1, 10)
                println(DI.sessionController.takeSeat(sessionId, rowNumber, seatNumber))
            }

            6 -> {
                break
            }
        }
    }
}