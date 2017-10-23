package com.example.demo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import com.example.demo.models.User
import com.example.demo.services.BirthmarkExtracter
import com.example.demo.services.BirthmarkPoster
import org.springframework.http.HttpStatus
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
                             @RequestPart("threshold") threshold: String): List<String?> {
        val poster = BirthmarkPoster()
        val result = File(BirthmarkExtracter(file, birthmark).extract()).readLines()
            .map{ n -> poster.post(n, birthmark, threshold) }
        return result
    }

	@GetMapping("/{name}")
	fun helloName(@PathVariable name:String)
			= name

    /** Cause an error to occur */
    @RequestMapping("/raiseError")
    fun raiseError() {
        throw IllegalArgumentException("This shouldn't have happened")
    }

    /** Handle the error */
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleError(e: IllegalArgumentException) = e.message

}
