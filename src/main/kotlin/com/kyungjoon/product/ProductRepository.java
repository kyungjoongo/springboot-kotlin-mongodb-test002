package com.kyungjoon.product;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, Long> {

}
