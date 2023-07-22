package com.kyungjoon.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class CellPhoneController(@Autowired val cellPhoneRepo: CellPhoneRepo) {

    @GetMapping("/cellPhone")
    fun getCount(): MutableList<CoffeeModel> {
        val direction = Sort.by(Sort.Direction.DESC, "id")
        println("sdflksdkfsdlkf===>>>>>>>")
        return cellPhoneRepo.findAll(direction)
    }

    @PostMapping("/cellPhone")
    fun postClient(@RequestBody coffeeModel: CoffeeModel): CoffeeModel {
        return cellPhoneRepo.insert(coffeeModel)
    }



    @GetMapping("/cellPhone/{id}")
    fun getClientById(@PathVariable("id") id: String): Optional<CoffeeModel> {
        return cellPhoneRepo.findById(id)
    }

    @DeleteMapping("/cellPhone/{id}")
    fun deleteClient(@PathVariable("id") id: String) {
        cellPhoneRepo.findById(id).let {
            cellPhoneRepo.deleteById(id)
        }
    }

    @DeleteMapping("/cellPhone/all")
    fun deleteAllClient() {
        println("sflksdlfksdlkf");
        cellPhoneRepo.deleteAll()
    }

    @PatchMapping("/cellPhone/{id}")
    fun updateClientModel(@PathVariable("id") id: String, @RequestBody coffeeModel: CoffeeModel): CoffeeModel? {
        return cellPhoneRepo.findById(id).let {
            cellPhoneRepo.save(coffeeModel)
        }
    }

}
