package data

import domain.entity.FilmEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

interface LoginDao {
    fun add(login: String, password: String)

    fun containsLogin(login: String): Boolean

    fun check(login: String, password: String): Boolean
}

class RuntimeLoginDao : LoginDao {
    private val users =
        try {
            Json.decodeFromString<MutableMap<String, String>>(File("src/main/resources/usersJson.json").readText())
        } catch (e: Exception) {
            mutableMapOf()
        }

    private fun updateJson() {
        val file = File("src/main/resources/usersJson.json")
        file.writeText(Json.encodeToString(users))
    }

    override fun add(login: String, password: String) {
        users[login] = password

        updateJson()
    }

    override fun containsLogin(login: String): Boolean {
        return users.containsKey(login)
    }

    override fun check(login: String, password: String): Boolean {
        return users[login] == password
    }

}