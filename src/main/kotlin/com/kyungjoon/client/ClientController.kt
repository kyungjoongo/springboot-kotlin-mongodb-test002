package com.kyungjoon.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ClientController(@Autowired val coffeeRepo: CoffeeRepo) {

    @GetMapping("/client")
    fun getCount(): MutableList<CoffeeModel> {
        val direction = Sort.by(Sort.Direction.DESC, "id")
        println("sdflksdkfsdlkf===>>>>>>>")
        return coffeeRepo.findAll(direction)
    }

    @PostMapping("/client")
    fun postClient(@RequestBody coffeeModel: CoffeeModel): CoffeeModel {
        return coffeeRepo.insert(coffeeModel)
    }

    @GetMapping("/client/{id}")
    fun getClientById(@PathVariable("id") id: String): Optional<CoffeeModel> {
        return coffeeRepo.findById(id)
    }

    @DeleteMapping("/client/{id}")
    fun deleteClient(@PathVariable("id") id: String) {
        coffeeRepo.findById(id).let {
            coffeeRepo.deleteById(id)
        }
    }

    @DeleteMapping("/client/all")
    fun deleteAllClient() {
        println("sflksdlfksdlkf");
        coffeeRepo.deleteAll()
    }

    @PatchMapping("/client/{id}")
    fun updateClientModel(@PathVariable("id") id: String, @RequestBody coffeeModel: CoffeeModel): CoffeeModel? {
        return coffeeRepo.findById(id).let {
            coffeeRepo.save(coffeeModel)
        }
    }

}
