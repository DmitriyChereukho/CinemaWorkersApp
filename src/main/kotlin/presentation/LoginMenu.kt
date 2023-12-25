package presentation

import di.DI

interface LoginMenu {
    fun readLoginAndPassword(): Pair<String, String>
}

class LoginMenuImpl : LoginMenu {
    override fun readLoginAndPassword(): Pair<String, String> {
        println("Введите логин:")
        val login = DI.reader.readNotNullString()
        println("Введите пароль:")
        val password = DI.reader.readNotNullString()
        return Pair(login, password)
    }

}
