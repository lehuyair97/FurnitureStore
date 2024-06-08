package com.example.mobileandroidapp_kotlin.ui.screens.tabscreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.mobileandroidapp_kotlin.Components.H3
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.SectionCustom
import com.example.mobileandroidapp_kotlin.Components.subText3
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()){
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(true)
    }
    val currentUser by viewModel.currentUser.collectAsState();
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp)) {
        HeadNav(
            Icons.Default.Search,
            title = "Profile",
            leadIconClick = {},
            traillingIcon = Icons.Default.Logout,
            traillingIconClick = {
                navController.navigate(
                    Screens.SignIn.route
                )
            },
            isLogOut = true
        )
        Spacer(Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ){
            Image(painter = rememberImagePainter(data = currentUser?.avatar ?: "https://storage.prompt-hunt.workers.dev/clfuo61qc000emn08vy1r44xh_1")
                , contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Fit)
            Column(modifier = Modifier
                .padding(start = 25.dp)
                .fillMaxWidth()){
                currentUser?.let { H3(label = it.userName) }
                currentUser?.let { subText3(label = it.email) }
            }
        }
        SectionCustom(title = "My Order", subTitle ="Already have 10 orders" ){
            navController.navigate(Screens.Notification.route)
        }
        SectionCustom(title = "Shipping Address", subTitle ="03 Address" ){
            navController.navigate(Screens.ShippingAdress.route)
        }

        SectionCustom(title = "Payment Method", subTitle ="You have 2 cards" ){
            navController.navigate(Screens.PaymentMethod.route)
        }
        SectionCustom(title = "My Review", subTitle ="Reviews for 5 items" )
        SectionCustom(title = "Setting", subTitle ="Notification, Password, FAQ, Contact" ){
            navController.navigate(Screens.SettingScreen.route)

        }

    }
}