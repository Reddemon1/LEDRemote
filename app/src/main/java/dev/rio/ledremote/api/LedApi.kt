package dev.rio.ledremote.api

import dev.rio.ledremote.model.Led
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LedApi {
    @GET("/led")
    suspend fun getLed() : MutableList<Led>;

    @PUT("/led/{id}")
    suspend fun editLed(@Path("id") id: Long , @Body led: Led) : MutableList<Led>
}