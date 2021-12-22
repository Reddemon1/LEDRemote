package dev.rio.ledremote.api

import com.google.gson.GsonBuilder
import dev.rio.ledremote.model.Led
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.88.247:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLed() : LedApi {
        return retrofit.create(LedApi::class.java)
    }
}