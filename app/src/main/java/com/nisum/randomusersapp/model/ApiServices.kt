package com.nisum.randomusersapp.model

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("api/")
    suspend fun getRandomUsers(@Query("results") results: Int): RandomUserResponse
}
