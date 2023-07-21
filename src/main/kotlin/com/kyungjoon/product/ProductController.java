package com.kyungjoon.product;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {

        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(this.productService.createProduct(product));
    }

    @Value("${gcp.config.file}")
    private String gcpConfigFile;


    @PostMapping("/upload2")
    public void upload2(@RequestParam("file") MultipartFile file) throws Exception {

        System.out.println(file);

        // The ID of your GCP project
        String projectId = "kotlin001";

        // The ID of your GCS bucket
        String bucketName = "upload2_justin";


        byte[] fileData = FileUtils.readFileToByteArray(convertFile(file));


        InputStream gcpCredentailInputStream = new ClassPathResource(gcpConfigFile).getInputStream();
        StorageOptions options = StorageOptions.newBuilder().setProjectId(projectId).setCredentials(GoogleCredentials.fromStream(gcpCredentailInputStream)).build();

        Storage storage = options.getService();
        Bucket bucket = storage.get(bucketName, Storage.BucketGetOption.fields());

        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String prefixName = originalFilename.split("\\.")[0];
        String ext = originalFilename.split("\\.")[1];
        System.out.println(originalFilename);
        String rString = this.getRandomString();

        String fullFileName = rString + "_" + prefixName + "." + ext;
        Blob blob = bucket.create("upload_temp" + "/" + fullFileName, fileData);

        if (blob != null) {
            System.out.println(("File successfully uploaded to GCS"));
        }


    }

    public String getRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 18;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    private String checkFileExtension(String fileName) throws Exception {
        if (fileName != null && fileName.contains(".")) {
            String[] extensionList = {".png", ".jpeg", ".pdf", ".doc", ".mp3"};

            for (String extension : extensionList) {
                if (fileName.endsWith(extension)) {
                    return extension;
                }
            }
        }
        throw new Exception("Not a permitted file type");
    }


    private File convertFile(MultipartFile file) throws Exception {
        try {
            if (file.getOriginalFilename() == null) {
                throw new Exception("Original file name is null");
            }
            File convertedFile = new File(file.getOriginalFilename());
            FileOutputStream outputStream = new FileOutputStream(convertedFile);
            outputStream.write(file.getBytes());
            outputStream.close();
            return convertedFile;
        } catch (Exception e) {
            throw new Exception("An error has occurred while converting the file");
        }
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) throws Exception {
        product.setId(id);
        return ResponseEntity.ok().body(this.productService.updateProduct(product));
    }

    @DeleteMapping("/products/{id}")
    public HttpStatus deleteProduct(@PathVariable long id) throws Exception {
        this.productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}
