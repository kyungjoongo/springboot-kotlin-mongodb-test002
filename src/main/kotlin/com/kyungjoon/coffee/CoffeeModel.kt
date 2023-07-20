package com.kyungjoon.client

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("coffee")
data class CoffeeModel(

    @Id
    var id: String? = ObjectId().toHexString(),

    val coffeeType: String= "",
    val smell: String = "",
    val favor: String = "",
    val origin: String = "",
    val shots: String = "",
    val isLatte: Boolean = false,



//    coffeeType: "sdlkflsdkflsdklfksdlfkksdlf",
//    smell: "sdlkflsdkflsdklfksdlfkksdlf",
//    favor: "sdlkflsdkflsdklfksdlfkksdlf",
//    origin: "sdlkflsdkflsdklfksdlfkksdlf",
//    shots: "sdlkflsdkflsdklfksdlfkksdlf",
//    isLatte: false,
)
