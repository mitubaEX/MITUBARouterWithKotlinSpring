package com.example.demo.services

import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class BirthmarkPoster(){
    fun post(data: String, birthmark: String, threshold: String): String{
        val url = "http://localhost:9000/upload"

        val json = JSONObject()
        json.put("data", data)
        json.put("birthmark", birthmark)
        json.put("threshold", threshold)
        val postBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json.toString())
        val request: Request = Request.Builder().url(url).post(postBody).build()
        println(request.body().toString())
        return json.toString()
    }
}
