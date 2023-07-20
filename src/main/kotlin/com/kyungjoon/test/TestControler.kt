package com.kyungjoon.test

import com.kyungjoon.restaurant.RestaurantRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*


@RequestMapping("/")
@RestController
class TestControler(@Autowired val restaurantRepo: RestaurantRepo) {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        return "server health is OK!!!!!!!!!!"
    }

    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String {
        return String.format("Hello %s!", name)
    }



}
