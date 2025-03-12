package com.shahbaz.learning.data.repository

import com.shahbaz.learning.data.model.Data
import com.shahbaz.learning.network.ApiService

class DataRepository(private val apiService: ApiService) {

    suspend fun getData(): Data {
        return apiService.getData()
    }
}