package com.kyungjoon.product2

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("product")
data class Product(

    @Id var id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: String? = null,
    val origin: String? = null,


)
