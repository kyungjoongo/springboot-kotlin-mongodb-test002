package com.kyungjoon.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ClientController(@Autowired val clientRepo: ClientRepo) {

    @GetMapping("/client")
    fun getCount(): MutableList<ClientModel> {
        val direction = Sort.by(Sort.Direction.DESC, "id")
        println("sdflksdkfsdlkf===>>>>>>>")
        return clientRepo.findAll(direction)
    }

    @PostMapping("/client")
    fun postClient(@RequestBody clientModel: ClientModel): ClientModel {
        return clientRepo.insert(clientModel)
    }

    @GetMapping("/client/{id}")
    fun getClientById(@PathVariable("id") id: String): Optional<ClientModel> {
        return clientRepo.findById(id)
    }

    @DeleteMapping("/client/{id}")
    fun deleteClient(@PathVariable("id") id: String) {
        clientRepo.findById(id).let {
            clientRepo.deleteById(id)
        }
    }

    @DeleteMapping("/client/all")
    fun deleteAllClient() {
        println("sflksdlfksdlkf");
        clientRepo.deleteAll()
    }

    @PatchMapping("/client/{id}")
    fun updateClientModel(@PathVariable("id") id: String, @RequestBody clientModel: ClientModel): ClientModel? {
        return clientRepo.findById(id).let {
            clientRepo.save(clientModel)
        }
    }

}
