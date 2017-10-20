package com.example.demo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import com.example.demo.models.User
import com.example.demo.services.BirthmarkExtracter
import com.example.demo.services.BirthmarkPoster
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

@RestController
class AppController() {

	@GetMapping("/user")
    fun getUser(): User {
        val user = User(
            username = "grahamcox",
            screenName = "Graham"
        )
        return user
    }

	@PostMapping("/user")
    fun  registerUser(@RequestBody user: User): User {
        return user
    }

	@PostMapping("/file")
    fun  registerUserRequest(@RequestPart("file") file: MultipartFile,
                             @RequestPart("birthmark") birthmark: String,
                             @RequestPart("threshold") threshold: String): List<String?> {
        val poster = BirthmarkPoster()
        /* val result = File(BirthmarkExtracter(file, birthmark).extract()).readLines()                          */
        /*         .map { n ->  n.split(Regex(","), 4) }                                                         */
        /*         .filter { n -> n.size >= 4 }.map{ n -> n[3]}.map{ n -> poster.post(n, birthmark, threshold) } */
        val result = File(BirthmarkExtracter(file, birthmark).extract()).readLines()
            .map{ n -> poster.post(n, birthmark, threshold) }
        return result
    }

	@GetMapping("/{name}")
	fun helloName(@PathVariable name:String)
			= name
}
