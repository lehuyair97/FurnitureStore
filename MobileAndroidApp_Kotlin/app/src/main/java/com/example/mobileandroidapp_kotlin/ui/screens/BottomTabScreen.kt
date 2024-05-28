package com.example.mobileandroidapp_kotlin.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.model.NavHostTabContainer
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.model.bottomNavItems
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTabScreen(navHost: NavHostController, viewModel: MainViewModel ) {
    val showBottomNav by viewModel.showBottomNav.collectAsState()
    viewModel.setShowBottomNav(true)



    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val signInState by viewModel.signInState.collectAsState()
    Scaffold(
        bottomBar = {
            if(showBottomNav){
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
                                item.icon?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = item.label
                                    )
                                }
                            },
                            label = { Text(text = item.label) }
                        )
                    }
                }
            }
        }
    ) {
        padding ->
        NavHostTabContainer(navController = navController, modifier = Modifier.padding(padding),viewModel = viewModel)
    }

}



