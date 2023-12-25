package domain

import data.LoginDao
import presentation.models.LoginOutputModel


interface LoginController {
    fun addUser(login: String, password: String): LoginOutputModel

    fun checkUser(login: String, password: String): LoginOutputModel
}

class LoginControllerImpl(private val loginDao: LoginDao) : LoginController {
    override fun addUser(login: String, password: String): LoginOutputModel {
        if (loginDao.containsLogin(login)) {
            return LoginOutputModel("Пользователь с таким логином уже существует", false)
        }
        loginDao.add(login, password)
        return LoginOutputModel("Регистрация завершена", true)
    }

    override fun checkUser(login: String, password: String): LoginOutputModel {
        if (!loginDao.containsLogin(login)) {
            return LoginOutputModel("Пользователя с таким логином не существует", false)
        }
        if(!loginDao.check(login, password)){
            return LoginOutputModel("Пароль неверный", false)
        }
        return LoginOutputModel("Авторизация завершена", true)
    }
}