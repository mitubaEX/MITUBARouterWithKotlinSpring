package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController() {

	@GetMapping("/")
	fun hello() = "hello"

	@GetMapping("/{name}")
	fun helloName(@PathVariable name:String)
			= name
}
