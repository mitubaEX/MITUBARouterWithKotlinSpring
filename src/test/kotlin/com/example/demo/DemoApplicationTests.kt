package com.example.demo

import com.example.demo.models.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun errorHandlingTest() {
		assert(
				"This shouldn't have happened".equals(restTemplate.getForEntity("/raiseError", String::class.java).body)
		)
	}

	@Test
	fun userTest(){
		val user = """{"username":"grahamcox","screenName":"Graham"}"""
		assert(user.equals(restTemplate.getForEntity("/user", String::class.java).body))
	}

}
