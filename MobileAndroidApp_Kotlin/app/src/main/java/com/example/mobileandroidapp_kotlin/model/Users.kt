package com.example.mobileandroidapp_kotlin.model


data class Users(
    val  _id: String ,
    var userName: String,
    var email: String,
    var password: String,
    var avatar: String? =null,
    var address: List<Address>?=null,
    var phoneNumber: Long? = null,
    var favorites : List<Carts>?= null,
    val paymentMethod: List<PaymentMethod>?= null

)
