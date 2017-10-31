package com.example.demo.services

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File

object server : PropertyGroup(){
    val port by intType
    val host by stringType
}

class BirthmarkPoster(){
    fun post(data: String, birthmark: String, threshold: String): String?{
        // conf/server.propertiesを参照する
        val config = systemProperties() overriding
                EnvironmentVariables() overriding
                ConfigurationProperties.fromFile(File("./conf/server.properties"))

        val url = "http://${config[server.host]}:${config[server.port]}/search"
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
