package com.shahbaz.learning.utils

fun String.formatToTwoDecimalPlaces(): String {
    return String.format("%.2f", this.toDouble())
}