package domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class FilmEntity(val title: String, val id: Int){
    override fun toString(): String {
        return " - \"$title\" id: $id"
    }
}