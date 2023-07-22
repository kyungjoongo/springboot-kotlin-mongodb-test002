package com.kyungjoon.product;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

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
    public ResponseEntity<Product> getProductById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(productService.getProductById(id));
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

    public String getHexString() {
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(0x10) + 0x10; // Generates a random number between 0x10 and 0x20
        System.out.printf("%x\n", myRandomNumber); // Prints it in hex, such as "0x14"
        String result = Integer.toHexString(myRandomNumber); //

        return result;
    }


    @DeleteMapping("/products/all")
    public ResponseEntity<Boolean> deleteProducts() throws Exception {

        boolean result = this.productService.deleteAll();
        return ResponseEntity.ok().body(result);
    }


//    @PutMapping("/products/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) throws Exception {
//        product.setId(id);
//        return ResponseEntity.ok().body(this.productService.updateProduct(product));
//    }

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
