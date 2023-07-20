package com.kyungjoon.client

import org.springframework.data.mongodb.repository.MongoRepository

interface CoffeeRepo : MongoRepository<CoffeeModel, String> {

}
