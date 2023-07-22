package com.kyungjoon.singer

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/singer")
@RestController
class SingerController(@Autowired val singerRepository: SingerRepository) {

    @PostMapping()
    fun create(@RequestBody singerModel: SingerModel): Any {
        return try {
            singerModel.id = ObjectId.get().toHexString()
            val productOne = singerRepository.save(singerModel)
            ResponseEntity.ok().body(productOne)
        } catch (e: Exception) {
            println(e.toString())
            ResponseEntity.badRequest()
        }
    }

    @GetMapping()
    fun getAll(): Any {
        return try {
            val direction = Sort.by(Sort.Direction.DESC, "id")
            val products = singerRepository.findAll(direction)
            ResponseEntity.ok().body(products)
        } catch (e: Exception) {
            ResponseEntity.badRequest()
        }
    }

    @GetMapping("/{id}")
    @Throws(Exception::class)
    fun getOneById(@PathVariable id: String): Any {
        return try {
            val product = singerRepository.findById(id)
            ResponseEntity.ok().body(product)
        } catch (e: Exception) {
            ResponseEntity.badRequest()
        }
    }


    @DeleteMapping("/all")
    @Throws(Exception::class)
    fun deleteOne(): Any {
        return try {
            singerRepository.deleteAll()
            ResponseEntity.ok().body(true)
        } catch (e: Exception) {
            ResponseEntity.badRequest()
        }
    }


    @PutMapping("/{id}")
    @Throws(Exception::class)
    fun updateOne(@PathVariable id: String, @RequestBody singerModel: SingerModel): SingerModel {
        singerModel.id = id
        val dataOne = singerRepository.findById(singerModel.id!!)
        return if (dataOne.isPresent) {
            val singerOldOne = dataOne.get()
            singerOldOne.id = singerModel.id
            singerOldOne.name = singerModel.name
            singerOldOne.sex = singerModel.sex
            singerRepository.save(singerOldOne)
            singerOldOne
        } else {
            throw Exception("Record not found with id : " + singerModel.id)
        }
    }

    @DeleteMapping("/{id}")
    @Throws(Exception::class)
    fun deleteOne(@PathVariable id: Long): Any {
        return try {
            singerRepository.deleteAll()
            ResponseEntity.ok()
        } catch (e: Exception) {
            println(e.toString())
            ResponseEntity.badRequest()
        }

    }
}
