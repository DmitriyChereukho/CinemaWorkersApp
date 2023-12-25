package domain.entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDateTime

@Serializable
data class SessionEntity(
    val filmEntity: FilmEntity,
    @Contextual val dateTime: LocalDateTime,
    @Contextual val cinemaHall: CinemaHall,
    val id: Int
) {
    override fun toString(): String {
        return " - Фильм: ${filmEntity.title} Время сеанса: ${dateTime.date} ${dateTime.time} id: $id"
    }
}