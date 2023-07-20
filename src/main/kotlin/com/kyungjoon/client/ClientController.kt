package com.kyungjoon.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ClientController(@Autowired val cellPhoneRepo: CellPhoneRepo) {

    @GetMapping("/client")
    fun getCount(): MutableList<CellPhoneModel> {
        val direction = Sort.by(Sort.Direction.DESC, "id")
        println("sdflksdkfsdlkf===>>>>>>>")
        return cellPhoneRepo.findAll(direction)
    }

    @PostMapping("/client")
    fun postClient(@RequestBody cellPhoneModel: CellPhoneModel): CellPhoneModel {
        return cellPhoneRepo.insert(cellPhoneModel)
    }

    @GetMapping("/client/{id}")
    fun getClientById(@PathVariable("id") id: String): Optional<CellPhoneModel> {
        return cellPhoneRepo.findById(id)
    }

    @DeleteMapping("/client/{id}")
    fun deleteClient(@PathVariable("id") id: String) {
        cellPhoneRepo.findById(id).let {
            cellPhoneRepo.deleteById(id)
        }
    }

    @DeleteMapping("/client/all")
    fun deleteAllClient() {
        println("sflksdlfksdlkf");
        cellPhoneRepo.deleteAll()
    }

    @PatchMapping("/client/{id}")
    fun updateClientModel(@PathVariable("id") id: String, @RequestBody cellPhoneModel: CellPhoneModel): CellPhoneModel? {
        return cellPhoneRepo.findById(id).let {
            cellPhoneRepo.save(cellPhoneModel)
        }
    }

}
