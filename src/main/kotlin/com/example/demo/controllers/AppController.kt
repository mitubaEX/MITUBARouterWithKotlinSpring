package com.example.demo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import com.example.demo.models.User
import com.example.demo.services.BirthmarkExtracter
import java.io.File

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
                             @RequestPart("threshold") threshold: String): String {
        println(BirthmarkExtracter(file, birthmark).extract())
        return birthmark
    }

	@GetMapping("/{name}")
	fun helloName(@PathVariable name:String)
			= name
}
