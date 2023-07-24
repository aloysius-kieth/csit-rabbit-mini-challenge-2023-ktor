package com.example

import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonRepresentation
import org.litote.kmongo.Id
import java.util.Date

data class Hotel(
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    val id: Id<Hotel>? = null,

    val city: String,
    val hotelName: String,
    val price: Int,
    val date: Date
)
