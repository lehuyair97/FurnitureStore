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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.BottomNavigationItem
import com.example.mobileandroidapp_kotlin.model.NavHostTabContainer
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.model.bottomNavItems
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.FavoriteScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.HomeScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.NotificationScreens
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.ProfileScreen
import com.example.mobileandroidapp_kotlin.viewmodal.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTabScreen(viewModel: MainViewModel = viewModel()) {

    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar{
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route){
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }

                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(text = item.label) }
                    )
                }
            }
        }
    ) {
        padding ->
        NavHostTabContainer(navController = navController, modifier = Modifier.padding(padding))
    }


}



