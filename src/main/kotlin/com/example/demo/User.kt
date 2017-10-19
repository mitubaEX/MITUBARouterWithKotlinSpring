package com.example.demo

import com.fasterxml.jackson.annotation.JsonCreator

data class User @JsonCreator constructor(
    val username: String,
    val screenName: String
)
