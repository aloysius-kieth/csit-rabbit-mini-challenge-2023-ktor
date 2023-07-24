package com.example.plugins

import com.example.*
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import toDto

val service = MongoDBService()

fun Application.configureRouting() {
    routing {
        route("/flight"){
            get{
                val departureDate = call.request.queryParameters["departureDate"].toString()
                val destination = call.request.queryParameters["destination"].toString()
                val returnDate = call.request.queryParameters["returnDate"].toString()
                var validInputs = true

                if(departureDate.isNullOrEmpty() || !Utils.isISOFormatDate(departureDate) ){
                    validInputs = false
                }
                if(returnDate.isNullOrEmpty() || !Utils.isISOFormatDate(returnDate) ){
                    validInputs = false
                }
                if(destination.isNullOrEmpty()){
                    validInputs = false
                }

                if(validInputs){
                    // Use async to call the first service function
                    val flightListDeferred = async { service.findDepartureFlights(departureDate, destination) }

                    // Use await to get the result of the first service call
                    val departureList = flightListDeferred.await().map(Flight::toDto)

                    // Call the second service function synchronously using runBlocking
                    runBlocking {
                        val returnList = service.findReturnFlights(returnDate, destination).map(Flight::toDto)

                        val result = mutableListOf<ResultFlightDataDto>()

                        for (i in 0 until returnList.size){
                            val d = departureList[i]
                            val r  = returnList[i]

                            result.add(ResultFlightDataDto(
                                destination,
                                departureDate,
                                d.airline,
                                d.price,
                                returnDate,
                                r.airline,
                                r.price
                            ))
                        }
                        call.respond(result)
                    }
                } else{
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_INPUT)
                }
            }
        }
        route("/hotel"){
            get{
                val destination = call.request.queryParameters["destination"].toString()
                val checkInDate = call.request.queryParameters["checkInDate"].toString()
                val checkOutDate = call.request.queryParameters["checkOutDate"].toString()

                var validInputs = true

                if(checkInDate.isNullOrEmpty() || !Utils.isISOFormatDate(checkInDate) ){
                    validInputs = false
                }
                if(checkOutDate.isNullOrEmpty() || !Utils.isISOFormatDate(checkOutDate) ){
                    validInputs = false
                }
                if(destination.isNullOrEmpty()){
                    validInputs = false
                }

                if(validInputs){
                    val hotels = service.findCheapestHotel(checkInDate, checkOutDate, destination).map(Hotel::toDto)
                    val result = mutableListOf<ResultHotelDto>()

                    val uniqueHotelNames = hotels.map { hotel-> hotel.hotelName }.distinct().toList()
                    uniqueHotelNames.map { hotelName ->
                        val price = hotels.filter { it.hotelName == hotelName }
                        val combinedPrice = price.sumOf { it.price }

                        result.add(
                            ResultHotelDto(
                            hotelName,
                            checkInDate,
                            checkOutDate,
                            hotelName,
                            combinedPrice
                            ))
                    }
                    call.respond(result.sortedBy { it.price })
                } else{
                    call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_INPUT)
                }
            }
        }
    }
}
