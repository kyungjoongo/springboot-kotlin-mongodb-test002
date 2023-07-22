package com.kyungjoon.singer

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("singer")
data class SingerModel(

    @Id var id: String? = null,
    var name: String? = null,
    var titleSong: String? = null,
    var sex: String? = null,
    val nation: String? = "us",
    val isMale: Boolean = false,




//     name: "sdflksdlfk",
//     titleSong: "sdflksdlfk",
//sex: "sdflksdlfk",
//nation: "sdflksdlfk",
//isMale: "sdflksdlfk",

)
