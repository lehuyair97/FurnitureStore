package com.example.mobileandroidapp_kotlin.model


data class Order(
    val _id: String? = null,
    val customerId: String?,
    var customerName: String,
    var customerEmail: String,
    var customerAddress: String?,
    var customerPhoneNumber: Long,
    var deliveryMethod: String,
    var paymentMethod: String,
    var totalBill: Double? = null,
    var carts: List<Carts>?= null,
    val orderDate: String?=null
)
