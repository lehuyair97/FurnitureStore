package com.example.mobileandroidapp_kotlin.model

data class Carts(
    val _id: String,
    val name: String,
    val price: Float,
    val imageLink: String,
    val quantity:Int ?= 1
)
