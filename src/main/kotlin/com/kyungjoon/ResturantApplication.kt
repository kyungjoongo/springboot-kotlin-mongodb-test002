package com.kyungjoon

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ResturantApplication

fun main(args: Array<String>) {
	runApplication<ResturantApplication>(*args) {

		setBannerMode(Banner.Mode.OFF)
	}
}
