package com.example.randomfacts.di

import com.example.randomfacts.network.FactApiService
import com.example.randomfacts.repository.FactRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideApi(): FactApiService {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl("https://uselessfacts.jsph.pl/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(FactApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: FactApiService): FactRepository = FactRepository(api)
}
