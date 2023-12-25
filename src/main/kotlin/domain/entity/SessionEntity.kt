package domain.entity

data class SessionEntity(
    val filmEntity: FilmEntity,
    val time: Int,
    val cinemaHall: CinemaHall,
    val id: Int
) {
    override fun toString(): String {
        return " - Фильм: $filmEntity Время сеанса: $time id: $id"
    }
}