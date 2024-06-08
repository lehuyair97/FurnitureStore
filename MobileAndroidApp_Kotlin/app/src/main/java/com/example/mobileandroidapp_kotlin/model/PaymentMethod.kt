package com.example.mobileandroidapp_kotlin.model

data class PaymentMethod(
    val CardNumber: Long,
    val CVV: Int,
    val YearMonth: String
)
