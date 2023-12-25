package domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SeatEntity(val row: Int, val seatNumber: Int, var seatState: Int)