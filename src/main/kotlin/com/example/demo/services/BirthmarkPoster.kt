package com.example.demo.services

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class BirthmarkPoster(){
    fun post(data: String, birthmark: String, threshold: String): String?{
        val url = "http://localhost:9000/search"
        val client: OkHttpClient = OkHttpClient.Builder().build()

        // create json
        val json = JSONObject()
        json.put("data", data)
        json.put("birthmark", birthmark)
        json.put("threshold", threshold)

        // post
        val postBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString())
        val request: Request = Request.Builder().url(url).post(postBody).build()
        val response = client.newCall(request).execute()
        println(response.isSuccessful)
        val result: String? = response.body()?.string()
        response.close()
        return result
    }
}
