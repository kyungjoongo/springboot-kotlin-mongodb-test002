package com.kyungjoon.restaurant

import org.springframework.data.mongodb.repository.MongoRepository

interface RestaurantRepo : MongoRepository<Restaurant, String> {

    fun findByRestaurantId(id: String): Restaurant?
}
