package com.example

import kotlinx.serialization.Serializable
import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonRepresentation
import org.litote.kmongo.Id
import java.time.ZonedDateTime
import java.util.Date

data class Flight(
  @BsonId
  @BsonRepresentation(BsonType.OBJECT_ID)
  val id: Id<Flight>? = null,

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
  val date: Date
)
