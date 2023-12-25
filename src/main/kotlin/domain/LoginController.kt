package domain

import data.LoginDao
import presentation.models.LoginOutputModel
import java.security.MessageDigest


interface LoginController {
    fun addUser(login: String, password: String): LoginOutputModel

    fun checkUser(login: String, password: String): LoginOutputModel
}

class LoginControllerImpl(private val loginDao: LoginDao) : LoginController {
    override fun addUser(login: String, password: String): LoginOutputModel {
        if (loginDao.containsLogin(login)) {
            return LoginOutputModel("Пользователь с таким логином уже существует", false)
        }
        loginDao.add(login, hashPassword(password))
        return LoginOutputModel("Регистрация завершена", true)
    }

    override fun checkUser(login: String, password: String): LoginOutputModel {
        if (!loginDao.containsLogin(login)) {
            return LoginOutputModel("Пользователя с таким логином не существует", false)
        }
        if (!loginDao.check(login, hashPassword(password))) {
            return LoginOutputModel("Пароль неверный", false)
        }
        return LoginOutputModel("Авторизация завершена", true)
    }

    private fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(password.toByteArray(Charsets.UTF_8))
        return bytesToString(bytes)
    }


    private fun bytesToString(bytes: ByteArray): String {
        val stringBuilder = StringBuilder()
        for (byte in bytes) {
            stringBuilder.append(String.format("%02x", byte))
        }
        return stringBuilder.toString()
    }
}