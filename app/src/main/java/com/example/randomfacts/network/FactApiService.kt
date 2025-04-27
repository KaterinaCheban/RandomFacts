package com.example.randomfacts.network

import com.example.randomfacts.model.Fact
import retrofit2.http.GET

interface FactApiService {
    @GET("random.json?language=en")
    suspend fun getRandomFact(): Fact

}