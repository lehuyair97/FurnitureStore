package com.example.mobileandroidapp_kotlin.ui.screens.tabscreens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()){
    Text("Profile Screen")
}