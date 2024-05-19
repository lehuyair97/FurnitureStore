package com.example.mobileandroidapp_kotlin.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.BottomNavigationItem
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.CartScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.HomeScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.ProfileScreen
import com.example.mobileandroidapp_kotlin.viewmodal.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTabScreen(viewModel: MainViewModel = viewModel()) {
    val items = listOf(
        BottomNavigationItem(title = "Home", route = "home", icon = Icons.Filled.Home),
        BottomNavigationItem(title = "Cart", route = "cart", icon = Icons.Filled.ShoppingCart),
        BottomNavigationItem(title = "Profile", route = "profile", icon = Icons.Filled.Person)
    )
    val navController = rememberNavController()
    val selectedTab by viewModel.selectedTab.collectAsState()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("cart") { CartScreen() }
        composable("profile") { ProfileScreen() }
    }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
}



