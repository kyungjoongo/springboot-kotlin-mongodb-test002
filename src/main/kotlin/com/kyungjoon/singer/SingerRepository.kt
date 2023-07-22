package com.kyungjoon.singer

import org.springframework.data.mongodb.repository.MongoRepository

interface SingerRepository : MongoRepository<SingerModel, String> {

}
