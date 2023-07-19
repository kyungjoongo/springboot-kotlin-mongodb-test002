package com.kyungjoon.client

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("client")
data class ClientModel(

    @Id
    var id: String? = ObjectId().toHexString(),

    val name: String= "",
    val nickname: String = "",
    val age: String = "",
    val sex: String = "",
    val college: String = "",
    val location: String = ""
)
