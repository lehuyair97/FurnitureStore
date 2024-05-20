package com.example.mobileandroidapp_kotlin

import SplashScreen
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.model.NavHostAppContainer
import com.example.mobileandroidapp_kotlin.ui.screens.BottomTabScreen
import com.example.mobileandroidapp_kotlin.ui.screens.DetailScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignInScreen
import com.example.mobileandroidapp_kotlin.ui.screens.SignUpScreen
import com.example.mobileandroidapp_kotlin.ui.screens.tabscreens.HomeScreen
import com.example.mobileandroidapp_kotlin.ui.theme.MobileAndroidApp_KotlinTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )

            MyApp()
        }
    }
}

@Composable
fun MyApp() {


    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
          
    ) {
        NavHostAppContainer(navController)
//        DetailScreen(navController = navController)
    }

}