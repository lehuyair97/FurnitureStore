package com.example.mobileandroidapp_kotlin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.ShippingAddressItem
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Address
import com.example.mobileandroidapp_kotlin.model.PaymentMethod
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ShippingAddress(navController:NavController, viewModel: MainViewModel){
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    val user by viewModel.currentUser.collectAsState();
    val addressList: List<Address> = user?.address ?: emptyList()
    val indexAddressSelected by viewModel.indexAddressSelected.collectAsState();
    var isCheckedList by remember { mutableStateOf(List(1) { false }) }
    if(addressList.isNotEmpty()){
        isCheckedList = List(addressList.size){false};
        isCheckedList = isCheckedList.toMutableList().apply { this[indexAddressSelected] = true };

    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp,)
    ){
        HeadNav(Icons.Default.ArrowBackIos, title = "Shpping address", leadIconClick = {
            navController.popBackStack()
        //            })
        })
        if(addressList.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                itemsIndexed(addressList) { index,address ->
                    user?.userName?.let { userName ->
                        ShippingAddressItem(userName = userName, address = address, isChecked = isCheckedList.get(index),
                            onCheckedChange = { isChecked ->
                                isCheckedList = isCheckedList.mapIndexed { currentIndex,_ ->
                                    viewModel.setIndexAddressSelected(index)
                                    currentIndex == index && isChecked
                                }
                            }
                        )
                    }
                }
            }
        }else{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(top = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(text = "No Shipping Address found.")
            }
        }
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()){
            Row(){
                Spacer(modifier = Modifier
                    .height(5.dp)
                    .weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 80.dp, end = 50.dp)
                        .shadow(elevation = 10.dp, shape = CircleShape, clip = true)
                        .size(60.dp)
                        .background(Color.White, shape = CircleShape)
                        .padding(20.dp)

                        .clickable {
                            navController.navigate(Screens.AddShippingAddress.route)
                        },
                    contentScale = ContentScale.FillBounds
                )
            }


            }
        }
    }
}