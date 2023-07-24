package com.example

import com.mongodb.client.model.Filters
import org.litote.kmongo.*
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.regex
import java.text.SimpleDateFormat
import java.util.*

class MongoDBService {
    private val connectionString: String = "mongodb+srv://userReadOnly:7ZT817O8ejDfhnBM@minichallenge.q4nve1r.mongodb.net/"
    private val client = KMongo.createClient(connectionString)
    private val database = client.getDatabase("minichallenge")
    private val flightCollection = database.getCollection<Flight>("flights")
    private val hotelCollection = database.getCollection<Hotel>("hotels")

//    fun findAllFlights(): List<Flight> =
//        flightCollection.find().toList()

    fun findDepartureFlights(departureDate: String, destination: String): List<Flight> {
        val departureDateISOUTC = Utils.stringToDateTimeISOUTC(departureDate)

        val srccityFilter = Flight::srccity.regex("singapore", "i")
        val destcityFilter = Flight::destcity.regex(destination, "i")
        val dateEq = Flight::date eq departureDateISOUTC

        val query = and(srccityFilter, destcityFilter,dateEq)

        return flightCollection.find(query).toList().sortedBy { it.price }
    }

    fun findReturnFlights(returnDate: String, source: String): List<Flight>{
        val returnDateISOUTC = Utils.stringToDateTimeISOUTC(returnDate)

        val srccityFilter = Flight::srccity.regex(source, "i")
        val destcityFilter = Flight::destcity.regex("singapore", "i")
        val dateEq = Flight::date eq returnDateISOUTC

        val query = and(srccityFilter, destcityFilter, dateEq)

        return flightCollection.find(query).toList().sortedBy { it.price }
    }

    fun findCheapestHotel(checkInDate: String, checkOutDate: String, destination :String): List<Hotel>{
        println(checkInDate)
        println(checkOutDate)
        println(destination)

        val checkInDateISOUTC = Utils.stringToDateTimeISOUTC(checkInDate)
        val checkOutDateISOUTC = Utils.stringToDateTimeISOUTC(checkOutDate)

        val checkInDateGte= Hotel::date.gte(checkInDateISOUTC)
        val checkOutDateLte= Hotel::date.gte(checkOutDateISOUTC)
        val destinationFilter = Hotel::city.regex(destination, "i")

        val query = and(destinationFilter, checkInDateGte, checkOutDateLte)

        return hotelCollection.find(query).toList()
    }
}