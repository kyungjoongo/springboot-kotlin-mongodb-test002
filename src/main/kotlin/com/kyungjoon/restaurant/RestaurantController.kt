package com.kyungjoon.restaurant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/restaurant")
@RestController
class RestaurantController(@Autowired val restaurantRepo: RestaurantRepo) {

    @GetMapping()
    fun getCount(): MutableList<Restaurant> {
        val direction = Sort.by(Sort.Direction.DESC, "id")

        println("sdlkfsldkflsdkflk====>>>>")
        return restaurantRepo.findAll(direction)
    }

    @PostMapping()
    fun postRestaurant(@RequestBody restaurant: Restaurant): Restaurant {
        return restaurantRepo.insert(restaurant)
    }

    @GetMapping("{id}")
    fun getRestaurantById(@PathVariable("id") id: String): Optional<Restaurant> {

        return restaurantRepo.findById(id)
    }

    @DeleteMapping("{id}")
    fun deleteRestaurant(@PathVariable("id") id: String) {
        restaurantRepo.findById(id).let {
            restaurantRepo.deleteById(id)
        }
    }

    @DeleteMapping("all")
    fun deleteAllRestaurnt() {
        println("sflksdlfksdlkf");
        restaurantRepo.deleteAll()
    }

    @PatchMapping("{id}")
    fun updateRestaurant(@PathVariable("id") id: String): Restaurant? {
        return restaurantRepo.findByRestaurantId(id)?.let {
            restaurantRepo.save(it.copy(name = "Update"))
        }
    }

}
