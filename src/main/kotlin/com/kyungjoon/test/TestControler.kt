package com.kyungjoon.test

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import com.kyungjoon.restaurant.RestaurantRepo
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*


@RequestMapping("/")
@RestController
class TestControler(@Autowired val restaurantRepo: RestaurantRepo) {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        return "server health is OK!!!!!!!!!!"
    }

    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String {
        return String.format("Hello %s!", name)
    }

    /**
     * todo: gcp config file load!
     */
    @Value("\${gcp.config.file}")
    private val gcpConfigFile: String? = null


    @PostMapping("/upload2")
    @Throws(Exception::class)
    fun upload2(@RequestParam("file") file: MultipartFile) {
        println(file)
        val gcpProjectId = "kotlin001"
        val gcpBucketName = "upload2_justin"
        val fileData = FileUtils.readFileToByteArray(convertFile(file))
        val gcpCredentailInputStream = ClassPathResource(gcpConfigFile!!).inputStream
        val options = StorageOptions.newBuilder().setProjectId(gcpProjectId)
            .setCredentials(GoogleCredentials.fromStream(gcpCredentailInputStream)).build()
        val storage = options.service
        val bucket = storage[gcpBucketName, Storage.BucketGetOption.fields()]
        val originalFilename = file.originalFilename!!
        val prefixName = originalFilename!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        val ext = originalFilename!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        println(originalFilename)
        val rString: String = this.getRandomString()
        val fullFileName = rString + "_" + prefixName + "." + ext
        val blob = bucket.create("upload_temp/$fullFileName", fileData)
        if (blob != null) {
            println("File successfully uploaded to GCS")
        }
    }

    fun getRandomString(): String {
        val leftLimit = 48 // numeral '0'
        val rightLimit = 122 // letter 'z'
        val targetStringLength = 18
        val random = Random()
        return random.ints(leftLimit, rightLimit + 1)
            .filter { i: Int -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) }
            .limit(targetStringLength.toLong())
            .collect(
                { StringBuilder() },
                { obj: java.lang.StringBuilder, codePoint: Int ->
                    obj.appendCodePoint(
                        codePoint
                    )
                }
            ) { obj: java.lang.StringBuilder, s: java.lang.StringBuilder? ->
                obj.append(
                    s
                )
            }
            .toString()
    }


    @Throws(java.lang.Exception::class)
    private fun convertFile(file: MultipartFile): File {
        return try {
            if (file.originalFilename == null) {
                throw java.lang.Exception("Original file name is null")
            }
            val convertedFile = File(file.originalFilename)
            val outputStream = FileOutputStream(convertedFile)
            outputStream.write(file.bytes)
            outputStream.close()
            convertedFile
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception("An error has occurred while converting the file")
        }
    }

}
