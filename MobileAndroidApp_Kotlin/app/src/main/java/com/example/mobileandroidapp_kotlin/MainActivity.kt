package com.example.mobileandroidapp_kotlin

import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.ui.screens.BottomTabScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignInScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignUpScreen
import com.example.mobileandroidapp_kotlin.ui.theme.MobileAndroidApp_KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("signin") { SignInScreen(navController) }
        composable("bottomtab") { BottomTabScreen() }
    }
}