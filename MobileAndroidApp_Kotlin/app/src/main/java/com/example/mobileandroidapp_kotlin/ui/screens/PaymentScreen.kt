package com.example.mobileandroidapp_kotlin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.CollapsePaymentItem
import com.example.mobileandroidapp_kotlin.Components.H4
import com.example.mobileandroidapp_kotlin.Components.H5
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.ShippingAddressItem
import com.example.mobileandroidapp_kotlin.Components.subText3
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Order
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController, viewModel: MainViewModel){
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    fun plus(firstNum: Double, secondNum:Int):Double{
        return firstNum+secondNum
    }
    val user by viewModel.currentUser.collectAsState();
    val indexAddressSelected by viewModel.indexAddressSelected.collectAsState();
    val indexPaymentSelected by viewModel.indexPaymentSelected.collectAsState();
    val cartsData by viewModel.getCart().collectAsState();
    val costProduct = cartsData
        .map { (it.quantity ?: 1) * it.price }
        .reduceOrNull { acc, cost -> acc + cost } ?: 0
    val totalCost = plus(costProduct.toDouble(), 5)
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
    ){
        HeadNav(Icons.Default.ArrowBackIos, title = "Check out", leadIconClick = {navController.navigate(
            Screens.BottomTab.route)})
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier= Modifier
                .padding(top = 20.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            subText3(label = "Shipping Address")
            Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(Screens.ShippingAdress.route)
                    })
        }
        if(user?.address?.isNotEmpty() == true){
            user?.userName?.let {(user!!.address?.get(indexAddressSelected))?.let { it1 -> ShippingAddressItem(userName = it, address = it1, isEdit = true) } }
        }
        Row(
            modifier= Modifier
                .padding(top = 20.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            subText3(label = "Payment")
            Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(Screens.PaymentMethod.route)
                    })
        }
        if(user?.paymentMethod?.isNotEmpty() == true){
            user?.paymentMethod?.let { (user!!.paymentMethod?.get(indexPaymentSelected))?.let { it1 ->CollapsePaymentItem (it1)} }
        }

        Row(
            modifier= Modifier
                .padding(top = 20.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            subText3(label = "Delivery method")
            Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                    })
        }
            Row(modifier = Modifier
                .padding(top = 2.dp)
                .fillMaxWidth()
                .shadow(5.dp, shape = RectangleShape, clip = true)
                .background(Color.White)
                .padding(15.dp)
            ){
                Image(painter = painterResource(id = R.drawable.img_logo_delivery), contentDescription = "", modifier = Modifier
                    .padding(end = 25.dp)
                    .width(100.dp)
                    .height(38.dp), contentScale = ContentScale.FillBounds)
                H5(label = "Fast (2-3days) " )
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
                        .padding(top = 2.dp)
                        .fillMaxWidth()
                        .shadow(5.dp, shape = RectangleShape, clip = true)
                        .background(Color.White)
                        .padding(20.dp)){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ){
                        subText3(label = "Order:")
                        H4(label = costProduct.toString() )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ){
                        subText3(label = "Delivery:")
                        H4(label = "$5.00" )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ){
                        subText3(label = "Total:")
                        H4(label = "$totalCost" )
                    }
                }
                Column(modifier = Modifier.padding(top=20.dp).fillMaxWidth()){
                    ButtonCustom(text = "SUBMIT ORDER") {
                        val order = user?.let {
                            user!!.phoneNumber?.let { it1 ->
                                Order(
                                    customerId = user?._id, customerName = it.userName,
                                    customerEmail = user!!.email, customerAddress = "${user!!.address?.get(indexAddressSelected)?.detail } +${
                                        user!!.address?.get(indexAddressSelected)?.location
                                    }", customerPhoneNumber = it1, deliveryMethod = "Fast (2-3days)", paymentMethod = "Visa Card" , totalBill = totalCost, carts = cartsData)
                            }
                        }
                        if (order != null) {
                            viewModel.sendPaymentToSever(order)
                            navController.navigate(Screens.PaymentSuccessScreen.route)
                        }
                    }
                }
            }
        }
    }
}