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

        val choice0 = readlnOrNull()?.toInt()
        when (choice0) {
            1 -> {
                while (true) {
                    println(DI.filmController.getAllToString())
                    println("1. Добавить фильм")
                    println("2. Удалить фильм")
                    println("3. Назад")
                    val choice1 = readlnOrNull()?.toInt()
                    when (choice1) {
                        1 -> {
                            println("Введите название фильма:")
                            DI.filmController.addFilm(readln())
                        }

                        2 -> {
                            println("Введите название фильма:")
                            DI.filmController.removeFilm(readln())
                        }

                        3 -> {
                            break
                        }
                    }
                }
            }

            2 -> {
                while (true) {
                    println(DI.sessionController.getAllToString())
                    println("1. Добавить сеанс")
                    println("2. Удалить сеанс")
                    println("3. Назад")
                    val choice2 = readlnOrNull()?.toInt()
                    when (choice2) {
                        1 -> {
                            println(DI.filmController.getAllToString())
                            println("Выберите фильм:")
                            val filmId = readln().toInt()
                            println("Выберите время:")
                            val time = readln().toInt()
                            DI.sessionController.addSession(filmId, time)
                        }

                        2 -> {
                            println("Выберите сеанс:")
                            val sessionId = readln().toInt()
                            DI.sessionController.removeSession(sessionId)
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
                println("Выберите сеанс")
                val sessionId = readln().toInt()
                println(DI.sessionController.getCinemaHallInfo(sessionId))

                println("Выберите номер ряда")
                val rowNumber = readln().toInt()
                println("Выберите номер места")
                val seatNumber = readln().toInt()
                DI.sessionController.sellTicket(sessionId, rowNumber, seatNumber)
            }

            4 -> {
                println("Текущее расписание:")
                println(DI.sessionController.getAllToString())
                println("Выберите сеанс")
                val sessionId = readln().toInt()
                println(DI.sessionController.getCinemaHallInfo(sessionId))

                println("Выберите номер ряда")
                val rowNumber = readln().toInt()
                println("Выберите номер места")
                val seatNumber = readln().toInt()
                DI.sessionController.returnTicket(sessionId, rowNumber, seatNumber)
            }

            5 -> {
                println("Текущее расписание:")
                println(DI.sessionController.getAllToString())
                println("Выберите сеанс")
                val sessionId = readln().toInt()
                println(DI.sessionController.getCinemaHallInfo(sessionId))

                println("Выберите номер ряда")
                val rowNumber = readln().toInt()
                println("Выберите номер места")
                val seatNumber = readln().toInt()
                DI.sessionController.takeSeat(sessionId, rowNumber, seatNumber)
            }
            6 ->{
                break
            }
        }
    }
}