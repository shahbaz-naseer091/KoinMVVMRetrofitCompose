package com.shahbaz.learning.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("data")
    val data: DataX = DataX(),
    @SerialName("message")
    val message: String = "",
    @SerialName("status")
    val status: Int = 0
)