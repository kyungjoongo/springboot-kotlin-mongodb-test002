package com.kyungjoon.restaurant

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("restaurants")
data class Restaurant(

    @Id
    var id: String? = ObjectId().toHexString(),

    val address: String= "",
    val borough: String = "",
    val cuisine: String = "",
    val grades: String = "",
    val name: String = "",
    val restaurantId: String = ""
)

data class Address(
    val building: String = "",
    val street: String = "",
    val zipcode: String = "",
    val coordinate: List<Double> = emptyList()
)

data class Grade(
    val date: Date = Date(),
    val rating: String = "",
    val score: Int = 0
)
