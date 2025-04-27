package com.example.randomfacts.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitInstance {
    private val json = Json { ignoreUnknownKeys = true }

    val api: FactApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://uselessfacts.jsph.pl/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(FactApiService::class.java)
    }
}
