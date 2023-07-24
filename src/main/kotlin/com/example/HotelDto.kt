package com.example

import kotlinx.serialization.SerialName
import java.util.Date

@kotlinx.serialization.Serializable
@SerialName("Hotel")
data class HotelDto(
    val id: String? = null,

    val city: String,
    val hotelName:String,
    val price: Int,
    @kotlinx.serialization.Serializable(with = DateSerializer::class)
    val date: Date
)

@kotlinx.serialization.Serializable
data class ResultHotelDto(
    @SerialName("City")
    val city: String,
    @SerialName("Check In Date")
    val checkInDate: String,
    @SerialName("Check Out Date")
    val checkOutDate: String,
    @SerialName("Hotel")
    val hotel: String,
    @SerialName("Price")
    val price: Int
)