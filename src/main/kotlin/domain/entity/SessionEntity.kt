package domain.entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class SessionEntity(
    val filmEntity: FilmEntity,
    val time: Int,
    @Contextual val cinemaHall: CinemaHall,
    val id: Int
) {
    override fun toString(): String {
        return " - Фильм: $filmEntity Время сеанса: $time id: $id"
    }
}