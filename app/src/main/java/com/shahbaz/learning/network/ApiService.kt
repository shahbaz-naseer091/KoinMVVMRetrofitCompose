package com.shahbaz.learning.network

import com.shahbaz.learning.data.model.Data
import retrofit2.http.GET

interface ApiService {
    @GET("productdetails/6701/253620?lang=en&store=KWD")
    suspend fun getData(): Data
}