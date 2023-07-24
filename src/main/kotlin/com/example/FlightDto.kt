package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@SerialName("Flight")
data class FlightDto(
    val id: String? = null,

    val airline: String,
    val airlineid: Int,
    val srcairport: String,
    val srcairportid: Int,
    val destairport: String,
    val destairportid: Int,
    val codeshare: String,
    val stop: Int,
    val eq: String,
    val airlinename: String,
    val srccity: String,
    val srccountry: String,
    val destairportname: String,
    val destcity: String,
    val destcountry: String,
    val price: Int,
    @Serializable(with = DateSerializer::class)
    val date: Date
)

@Serializable
data class ResultFlightDataDto(
    @SerialName("City")
    val City : String,
    @SerialName("Departure Date")
    val DepartureDate: String,
    @SerialName("Departure Airline")
    val DepartureAirline: String,
    @SerialName("Departure Price")
    val DeparturePrice: Int,
    @SerialName("Return Date")
    val ReturnDate: String,
    @SerialName("Return Airline")
    val ReturnAirline: String,
    @SerialName("Return Price")
    val ReturnPrice: Int,
)
