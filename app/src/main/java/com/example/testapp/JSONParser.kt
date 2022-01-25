package com.example.testapp

import android.content.Context
import android.util.Log
import java.io.InputStream

class JSONParser {

    fun loadJSON(context: Context): String? {
        var input: InputStream? = null
        val jsonString: String
        try {
            input = context.assets.open("pins.json")
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            jsonString = String(buffer)
            return jsonString
        }catch (e: Exception){
            Log.d("Error", "Something went wrong")
        } finally {
            input?.close()
        }
        return null
    }
}