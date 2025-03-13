package com.shahbaz.test.data.repository

import android.util.Log
import com.shahbaz.test.network.FakeApiService
import retrofit2.HttpException

class DataRepository(private val apiService: FakeApiService) {

    suspend fun getUsers(): List<String> {
        return try {
            apiService.getUsers()
        } catch (e: HttpException) {
            Log.d("Error Code 1", ""+e.code())
            return listOf("Shahbaz", "Zamir", "Karim", "kamil", "Ahmed")
        } catch (e: Exception) {
            Log.e("DataRepository", "Unexpected Error: ${e.message}")
            return listOf("Shahbaz", "Zamir", "Karim", "kamil", "Ahmed")
        }
    }

    suspend fun getCurrentTemperature(): String {
        return try {
            apiService.getCurrentTemperature()
        } catch (e: HttpException) {
            return "10.0 C"
        } catch (e: Exception) {
            Log.e("DataRepository", "Unexpected Error: ${e.message}")
            return "10.0 C"
        }
    }
}