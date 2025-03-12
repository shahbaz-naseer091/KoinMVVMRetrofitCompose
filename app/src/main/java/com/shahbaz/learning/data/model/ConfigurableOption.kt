package com.shahbaz.learning.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurableOption(
    @SerialName("attribute_code")
    val attributeCode: String = "",
    @SerialName("attribute_id")
    val attributeId: Int = 0,
    @SerialName("attributes")
    val attributes: List<Attribute> = listOf(),
    @SerialName("type")
    val type: String = ""
)