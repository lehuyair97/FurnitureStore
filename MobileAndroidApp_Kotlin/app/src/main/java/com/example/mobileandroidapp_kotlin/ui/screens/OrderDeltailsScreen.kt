package com.example.mobileandroidapp_kotlin.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.CollapseCartItem
import com.example.mobileandroidapp_kotlin.Components.H4
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.subText3
import com.example.mobileandroidapp_kotlin.model.Carts
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn as LazyColumn


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(navController: NavController, viewModel: MainViewModel){
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    val order by viewModel.lastPaymentSuccess.collectAsState();
    val carts : List<Carts>? = order?.carts;
    Log.e("Tag", "Values Carts ${carts?.toList()}")
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
    ){
        HeadNav(Icons.Default.ArrowBackIos, title = "Order Details", leadIconClick = {navController.popBackStack()})
        Text(text = "Bạn đã đặt đơn hàng thành công",style = TextStyle(fontWeight = FontWeight(400), fontSize = 15.sp, color = Color.Green),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
            )

        Column(
            modifier= Modifier
                .padding(top = 10.dp, bottom = 6.dp)
                .fillMaxWidth(),
        ){
            H4(label = "Customer's Information")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray))
            Spacer(modifier = Modifier.height(6.dp))
            order?.customerName?.let { subText3(label = it) }
            Spacer(modifier = Modifier.height(6.dp))
            order?.customerEmail?.let { subText3(label = it) }
            Spacer(modifier = Modifier.height(6.dp))
            order?.customerAddress?.let { subText3(label = it) }
            Spacer(modifier = Modifier.height(6.dp))
            subText3(label = "+${order?.customerPhoneNumber}")
        }

        Column(
            modifier= Modifier
                .padding(top = 10.dp, bottom = 6.dp)
                .fillMaxWidth(),
        ){
            H4(label = "Delivery Method")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray))
            Spacer(modifier = Modifier.height(6.dp))
            subText3(label = "${order?.deliveryMethod}" )
        }
        Column(
            modifier= Modifier
                .padding(top = 10.dp, bottom = 6.dp)
                .fillMaxWidth(),
        ){
            H4(label = "Payment Method")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray))
            Spacer(modifier = Modifier.height(6.dp))
            subText3(label = "${order?.paymentMethod}" )
        }
        Column(
            modifier= Modifier
                .padding(top = 10.dp, bottom = 6.dp)
                .fillMaxWidth()
                .height(280.dp),
        ){
            H4(label = "Items Selected")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray))
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)){
                itemsIndexed(carts ?: emptyList()){
                   index, item ->
                    CollapseCartItem(item)
                    if(index != carts?.size?.minus(1) ?: 0){
                        Spacer(modifier = Modifier.height(8.dp))
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(color = Color.Gray)
                        )
                    }
                }
            }
        }




        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)
        ) {
            Column(Modifier.fillMaxWidth()) {
                Column (
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(top = 2.dp)
                        .fillMaxWidth()
                        .shadow(5.dp, shape = RectangleShape, clip = true)
                        .background(Color.White)
                        .padding(10.dp)){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ){
                        subText3(label = "Total:")
                        H4(label = "${order?.totalBill}" )
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()){
                    ButtonCustom(text = "Back To Home Screen") {
                        navController.navigate(Screens.BottomTab.route)
                    }
                }
            }
        }
    }
}