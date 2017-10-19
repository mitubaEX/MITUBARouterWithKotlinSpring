package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController() {

	@GetMapping("/")
	fun hello() = "hello"

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

	@GetMapping("/{name}")
	fun helloName(@PathVariable name:String)
			= name
}
