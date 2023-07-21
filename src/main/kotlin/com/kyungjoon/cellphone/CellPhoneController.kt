package com.kyungjoon.client

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.nio.file.Paths
import java.util.*


@RestController
class CellPhoneController(@Autowired val coffeeRepo: CoffeeRepo) {

    @GetMapping("/cellPhone")
    fun getCount(): MutableList<CoffeeModel> {
        val direction = Sort.by(Sort.Direction.DESC, "id")
        println("sdflksdkfsdlkf===>>>>>>>")
        return coffeeRepo.findAll(direction)
    }

    @PostMapping("/cellPhone")
    fun postClient(@RequestBody coffeeModel: CoffeeModel): CoffeeModel {
        return coffeeRepo.insert(coffeeModel)
    }



    @GetMapping("/cellPhone/{id}")
    fun getClientById(@PathVariable("id") id: String): Optional<CoffeeModel> {
        return coffeeRepo.findById(id)
    }

    @DeleteMapping("/cellPhone/{id}")
    fun deleteClient(@PathVariable("id") id: String) {
        coffeeRepo.findById(id).let {
            coffeeRepo.deleteById(id)
        }
    }

    @DeleteMapping("/cellPhone/all")
    fun deleteAllClient() {
        println("sflksdlfksdlkf");
        coffeeRepo.deleteAll()
    }

    @PatchMapping("/cellPhone/{id}")
    fun updateClientModel(@PathVariable("id") id: String, @RequestBody coffeeModel: CoffeeModel): CoffeeModel? {
        return coffeeRepo.findById(id).let {
            coffeeRepo.save(coffeeModel)
        }
    }

}
