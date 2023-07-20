package com.kyungjoon.client

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("cellphone")
data class CellPhoneModel(

    @Id
    var id: String? = ObjectId().toHexString(),

    val modelName: String= "",
    val realName: String = "",
    val cpuCount: String = "",
    val gpu: String = "",
    val cpuType: String = "",
    val publishedDate: String = ""
)
