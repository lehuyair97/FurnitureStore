package com.example.mobileandroidapp_kotlin.model


import SplashScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.ui.screens.AddShippingAddress
import com.example.mobileandroidapp_kotlin.ui.screens.BottomTabScreen
import com.example.mobileandroidapp_kotlin.ui.screens.DetailScreen
import com.example.mobileandroidapp_kotlin.ui.screens.PaymentScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignInScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignUpScreen
import com.example.mobileandroidapp_kotlin.ui.screens.CartScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.FavoriteScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.HomeScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.NotificationScreens
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.ProfileScreen
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel

sealed class Screens(val route: String, val label: String, val icon: ImageVector?) {
    object Home : Screens("home", "Home", Icons.Filled.Home)
    object Favorite : Screens("favorite", "Favorite", Icons.Filled.Favorite)
    object Notification : Screens("notification", "Notification", Icons.Filled.Notifications)
    object Profile : Screens("profile", "Profile", Icons.Filled.Person)
    object SplashScreen : Screens("splash", "Splash", Icons.Filled.Home)
    object SignUp : Screens("signup", "Sign Up", Icons.Filled.PersonAdd)
    object SignIn : Screens("signin", "Sign In", Icons.Filled.Login)
    object Detail : Screens("detail", "Detail", Icons.Filled.Info)
    object BottomTab : Screens("bottomtab", "Bottom Tab", Icons.Filled.Menu)
    object Cart : Screens("cart", "Cart", null)
    object Payment : Screens("payment", "Payment", null)
    object AddShippingAddress : Screens("shippingAdress", "Add Shipping Address", null)
}



val bottomNavItems = listOf(
    Screens.Home,
    Screens.Favorite,
    Screens.Notification,
    Screens.Profile
)
@Composable
fun NavHostTabContainer(navController: NavHostController,viewModel : MainViewModel ,modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        composable(Screens.Home.route) { HomeScreen(navController,viewModel= viewModel) }
        composable(Screens.Favorite.route) { FavoriteScreen(navController,viewModel=viewModel) }
        composable(Screens.Notification.route) { NotificationScreens(navController,viewModel=viewModel) }
        composable(Screens.Profile.route) { ProfileScreen(navController, viewModel=viewModel) }
        composable(Screens.Detail.route) { DetailScreen(navController,viewModel =viewModel) }
        composable(Screens.BottomTab.route) { BottomTabScreen(navController,viewModel=viewModel) }
        composable(Screens.Cart.route) { CartScreen(navController,viewModel=viewModel) }
        composable(Screens.Payment.route) { PaymentScreen(navController,viewModel=viewModel) }
        composable(Screens.AddShippingAddress.route) { AddShippingAddress(navController,viewModel=viewModel) }
    }
}

@Composable
fun NavHostAppContainer(modifier: Modifier = Modifier,viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route,
        modifier = modifier
    ) {
        composable(Screens.SplashScreen.route) { SplashScreen(navController, viewModel = viewModel) }
        composable(Screens.SignUp.route) { SignUpScreen(navController,viewModel = viewModel,) }
        composable(Screens.SignIn.route) { SignInScreen(navController,viewModel=viewModel) }
        composable(Screens.BottomTab.route) { BottomTabScreen(navController,viewModel=viewModel) }
    }
}



