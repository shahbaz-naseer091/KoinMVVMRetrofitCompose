package com.shahbaz.test.network

import com.shahbaz.test.utils.API_KEY
import retrofit2.http.GET

interface FakeApiService {

    @GET("/users")
    suspend fun getUsers(): List<String> {
        return listOf("Shahbaz", "Zamir", "Karim", "kamil", "Ahmed")
    }

    @GET("/fetchWeather")
    suspend fun getCurrentTemperature() : String {
        return "10.0 C"
    }
}