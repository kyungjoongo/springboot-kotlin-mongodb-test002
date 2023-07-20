package com.kyungjoon.client

import org.springframework.data.mongodb.repository.MongoRepository

interface CellPhoneRepo : MongoRepository<CoffeeModel, String> {

}
