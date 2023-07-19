package com.kyungjoon.client

import org.springframework.data.mongodb.repository.MongoRepository

interface ClientRepo : MongoRepository<ClientModel, String> {

}
