package presentation

import di.DI
import kotlin.system.exitProcess

fun main() {

    while (true) {
        println("1. Зарегистрироваться")
        println("2. Авторизоваться")
        println("3. Выход")

        when (DI.reader.readNatural(1, 3)) {
            1 -> {
                val regPair = DI.loginMenu.readLoginAndPassword()
                val outputModel = DI.loginController.addUser(regPair.first, regPair.second)
                println(outputModel.message)
                if (outputModel.passed) {
                    break
                }
            }

            2 -> {
                val loginPair = DI.loginMenu.readLoginAndPassword()
                val outputModel = DI.loginController.checkUser(loginPair.first, loginPair.second)
                println(outputModel.message)
                if (outputModel.passed) {
                    break
                }
            }

            3 -> {
                exitProcess(0)
            }
        }
    }


    while (true) {
        println("1. Посмотреть текущий прокат")
        println("2. Посмотреть расписание сеансов")
        println("3. Продать билет")
        println("4. Возврат билета")
        println("5. Отметить занятое место на ближайшем сеансе")
        println("6. Посмотреть свободные и проданные места на сеансе")
        println("7. Выход")

        when (DI.reader.readNatural(1, 6)) {
            1 -> {
                DI.filmMenu.processMenu()
            }

            2 -> {
                DI.sessionMenu.processMenu()
            }

            3 -> {
                if (DI.sessionController.getSessionCount() == 0) {
                    println("В текущем расписании нет сеансов")
                    continue
                }
                val sessionId = DI.sessionMenu.readSessionId()
                val seatPair = DI.ticketMenu.readSeat()
                println(DI.sessionController.sellTicket(sessionId, seatPair.first, seatPair.second))
            }

            4 -> {
                if (DI.sessionController.getSessionCount() == 0) {
                    println("В текущем расписании нет сеансов")
                    continue
                }
                val sessionId = DI.sessionMenu.readSessionId()
                val seatPair = DI.ticketMenu.readSeat()
                println(DI.sessionController.returnTicket(sessionId, seatPair.first, seatPair.second))
            }

            5 -> {
                if (DI.sessionController.getSessionCount() == 0) {
                    println("В текущем расписании нет сеансов")
                    continue
                }
                val sessionId = DI.sessionMenu.readSessionId()
                val seatPair = DI.ticketMenu.readSeat()
                println(DI.sessionController.takeSeat(sessionId, seatPair.first, seatPair.second))
            }

            6 -> {
                if (DI.sessionController.getSessionCount() == 0) {
                    println("В текущем расписании нет сеансов")
                    continue
                }
                DI.sessionMenu.readSessionId()
            }

            7 -> {
                break
            }
        }
    }
}