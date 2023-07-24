package com.example

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

object Utils {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun isISOFormatDate(input: String): Boolean{
        println(input)
        return try{
            formatter.parse(input)
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }

    fun stringToDateTimeISOUTC(input: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        return formatter.parse(input)
    }
}