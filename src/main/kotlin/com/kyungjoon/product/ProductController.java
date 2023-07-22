package com.kyungjoon.product;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Object getAllProduct() {
        try {
            Sort direction = Sort.by(Sort.Direction.DESC, "id");
            List<Product> products = this.productRepository.findAll(direction);
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest();
        }
    }

    @GetMapping("/products/{id}")
    public Object getProductById(@PathVariable String id) throws Exception {
        try{
            Optional<Product> product= this.productRepository.findById(id);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest();
        }
    }

    @PostMapping("/products")
    public Object createProduct(@RequestBody Product product) {
        try {
            product.setId(ObjectId.get().toHexString());
            Product productOne = productRepository.save(product);
            return ResponseEntity.ok().body(productOne);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.badRequest();
        }
    }



    @DeleteMapping("/products/all")
    public Object deleteProducts() throws Exception {

        try{
            this.productRepository.deleteAll();;
            return ResponseEntity.ok().body(true);
        }catch (Exception e){
            return ResponseEntity.badRequest();
        }
    }


    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) throws Exception {
        product.setId(id);
        Optional<Product> productDb = this.productRepository.findById(product.getId());
        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            productRepository.save(productUpdate);
            return productUpdate;
        } else {
            throw new Exception("Record not found with id : " + product.getId());
        }
    }

    @DeleteMapping("/products/{id}")
    public Object deleteProduct(@PathVariable long id) throws Exception {
        try {
            this.productRepository.deleteAll();
            return ResponseEntity.ok();
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.badRequest();
        }
    }
}
