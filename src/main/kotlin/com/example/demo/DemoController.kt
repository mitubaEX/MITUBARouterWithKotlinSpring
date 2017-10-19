package com.example.demo

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class DemoController() {

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
        return birthmark
    }

	@GetMapping("/{name}")
	fun helloName(@PathVariable name:String)
			= name
}
