//package com.kyungjoon.product;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class ProductService {
//
//
//    @Autowired
//    private ProductRepository productRepository;
//
//
//    public Product createProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    public Product updateProduct(Product product) throws Exception {
//        Optional<Product> productDb = this.productRepository.findById(product.getId());
//
//        if (productDb.isPresent()) {
//            Product productUpdate = productDb.get();
//            productUpdate.setId(product.getId());
//            productUpdate.setName(product.getName());
//            productUpdate.setDescription(product.getDescription());
//            productRepository.save(productUpdate);
//            return productUpdate;
//        } else {
//            throw new Exception("Record not found with id : " + product.getId());
//        }
//    }
//
//    public List<Product> getAllProduct() {
//
//        List<Product> products = this.productRepository.findAll();
//
//        return products;
//    }
//
//    public Product getProductById(long productId) throws Exception {
//
//        Optional<Product> productDb = this.productRepository.findById(productId);
//
//        if (productDb.isPresent()) {
//            return productDb.get();
//        } else {
//            throw new Exception("Record not found with id : " + productId);
//        }
//    }
//
//    public void deleteProduct(long productId) throws Exception {
//        Optional<Product> productDb = this.productRepository.findById(productId);
//
//        if (productDb.isPresent()) {
//            this.productRepository.delete(productDb.get());
//        } else {
//            throw new Exception("Record not found with id : " + productId);
//        }
//
//    }
//
//    public boolean deleteAll() throws Exception {
//        try {
//            this.productRepository.deleteAll();
//            return true;
//        } catch (Exception ignored) {
//            return false;
//        }
//    }
//}
