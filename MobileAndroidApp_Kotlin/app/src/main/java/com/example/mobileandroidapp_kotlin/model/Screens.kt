package com.example.mobileandroidapp_kotlin.model


import SplashScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobileandroidapp_kotlin.ui.screens.BottomTabScreen
import com.example.mobileandroidapp_kotlin.ui.screens.DetailScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignInScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignUpScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.FavoriteScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.HomeScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.NotificationScreens
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.ProfileScreen
import com.example.mobileandroidapp_kotlin.viewmodal.MainViewModel

sealed class Screens(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screens("home", "Home", Icons.Filled.Home)
    object Favorite : Screens("favorite", "Favorite", Icons.Filled.Favorite)
    object Notification : Screens("notification", "Notification", Icons.Filled.Notifications)
    object Profile : Screens("profile", "Profile", Icons.Filled.Person)
}

val bottomNavItems = listOf(
    Screens.Home,
    Screens.Favorite,
    Screens.Notification,
    Screens.Profile
)
@Composable
fun NavHostTabContainer(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) { HomeScreen(navController,viewModel) }
        composable(Screens.Favorite.route) { FavoriteScreen() }
        composable(Screens.Notification.route) { NotificationScreens() }
        composable(Screens.Profile.route) { ProfileScreen() }
        composable("detail") {DetailScreen(navController,viewModel)}

    }
}

@Composable
fun NavHostAppContainer(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier) {
        composable("splash") { SplashScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("signin") { SignInScreen(navController) }
        composable("detail") {DetailScreen(navController)}
        composable("bottomtab") { BottomTabScreen() }
    }
}
