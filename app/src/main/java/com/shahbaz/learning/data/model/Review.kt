package com.shahbaz.learning.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("rating_count")
    val ratingCount: Int = 0,
    @SerialName("total_review")
    val totalReview: Int = 0
)