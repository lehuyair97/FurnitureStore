package com.example.mobileandroidapp_kotlin.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title:String,
    val route:String,
    val icon: ImageVector
)