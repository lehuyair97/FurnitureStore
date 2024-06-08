package com.example.mobileandroidapp_kotlin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.CollapsePaymentItem
import com.example.mobileandroidapp_kotlin.Components.H1
import com.example.mobileandroidapp_kotlin.Components.H4
import com.example.mobileandroidapp_kotlin.Components.H5
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.OutlineButtonCustom
import com.example.mobileandroidapp_kotlin.Components.ShippingAddressItem
import com.example.mobileandroidapp_kotlin.Components.subText3
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SuccessPaymentScreen(navController: NavController, viewModel: MainViewModel ){
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 110.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Success!",style = TextStyle(fontWeight = FontWeight(550), fontSize = 40.sp))
        Image(painter = painterResource(id = R.drawable.img_success) , contentDescription = "",
            modifier = Modifier
                .padding(vertical = 25.dp)
                .fillMaxWidth(0.8f)
                .aspectRatio(1.17f))
        Spacer(modifier = Modifier.height(20.dp))
        subText3(label = "Your order will be delivered soon.")
        Spacer(modifier = Modifier.height(10.dp))
        subText3(label = "Thank you for choosing our app.")
        Spacer(modifier = Modifier.height(45.dp))
        ButtonCustom(text = "Track your orders") {
            navController.navigate(Screens.OrderDetailsScreen.route)

        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlineButtonCustom(text = "BACK TO HOME"){
            navController.navigate(Screens.BottomTab.route)
        }
    }
}