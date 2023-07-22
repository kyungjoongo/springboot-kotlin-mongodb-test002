package com.kyungjoon.product2


import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ProductController(@Autowired val productRepository: ProductRepository) {


    @GetMapping("/products")
    fun getAllProduct(): Any {
        return try {
            val direction = Sort.by(Sort.Direction.DESC, "id")
            val products = productRepository.findAll(direction)
            ResponseEntity.ok().body(products)
        } catch (e: Exception) {
            ResponseEntity.badRequest()
        }
    }

    @GetMapping("/products/{id}")
    @Throws(Exception::class)
    fun getProductById(@PathVariable id: String): Any {
        return try {
            val product = productRepository.findById(id)
            ResponseEntity.ok().body(product)
        } catch (e: Exception) {
            ResponseEntity.badRequest()
        }
    }

    @PostMapping("/products")
    fun createProduct(@RequestBody product: Product): Any {
        return try {
            product.id = ObjectId.get().toHexString()
            val productOne = productRepository.save(product)
            ResponseEntity.ok().body(productOne)
        } catch (e: Exception) {
            println(e.toString())
            ResponseEntity.badRequest()
        }
    }


    @DeleteMapping("/products/all")
    @Throws(Exception::class)
    fun deleteProducts(): Any {
        return try {
            productRepository.deleteAll()
            ResponseEntity.ok().body(true)
        } catch (e: Exception) {
            ResponseEntity.badRequest()
        }
    }


//    @PutMapping("/products/{id}")
//    @Throws(Exception::class)
//    fun updateProduct(@PathVariable id: String?, @RequestBody product: Product): Product {
//        product.id = id
//        val productDb = productRepository.findById(product.id)
//        return if (productDb.isPresent) {
//            val productUpdate = productDb.get()
//            productUpdate.id = product.id
//            productUpdate.name = product.name
//            productUpdate.description = product.description
//            productRepository.save(productUpdate)
//            productUpdate
//        } else {
//            throw Exception("Record not found with id : " + product.id)
//        }
//    }

    @DeleteMapping("/products/{id}")
    @Throws(Exception::class)
    fun deleteProduct(@PathVariable id: Long): Any {
        return try {
            productRepository.deleteAll()
            ResponseEntity.ok()
        } catch (e: Exception) {
            println(e.toString())
            ResponseEntity.badRequest()
        }
    }
}
