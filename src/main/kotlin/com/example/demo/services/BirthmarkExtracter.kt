package com.example.demo.services

import com.github.pochi.runner.scripts.ScriptRunnerBuilder;
import org.springframework.web.multipart.MultipartFile
import java.io.File

import java.nio.file.Files
import java.nio.file.StandardCopyOption

class BirthmarkExtracter(val file: MultipartFile, val birthmark: String){
    fun extract(): String{
        val builder = ScriptRunnerBuilder()
        val runner = builder.build()
        val arg = arrayOf("./extract.js", createOriginalFile(), birthmark)
        runner.runsScript(arg)
        return "files/" + file.originalFilename + ".csv"
    }

    fun createOriginalFile(): String{
        val saveFile = File("files/" + file.originalFilename)
        Files.copy(file.inputStream, saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
        return saveFile.path
    }
}
