package com.example.mobileandroidapp_kotlin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.CartItem
import com.example.mobileandroidapp_kotlin.Components.H4
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.subText3
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen( navController: NavController , viewModel: MainViewModel  ) {
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    val cartsData by viewModel.getCart().collectAsState();
    val totalCost = cartsData
        .map { (it.quantity ?: 1) * it.price }
        .reduceOrNull { acc, cost -> acc + cost } ?: 0

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp,)
    ){
        HeadNav(Icons.Default.ArrowBackIos, title = "My Cart", leadIconClick = {navController.popBackStack()})
        Spacer(modifier = Modifier.height(15.dp))
        if(cartsData.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                itemsIndexed(cartsData) { index , item ->
                    CartItem(item, viewModel = viewModel, removeCart = {
                        viewModel.removeCart(cartsData.get(index))
                    } )
                    if(index != cartsData.size-1){
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
        }else{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(top = 190.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(Modifier.fillMaxWidth()
                ) {
                    Text(text = "There's no item in the Cart",style = TextStyle(fontWeight = FontWeight(500), fontSize = 18.sp),
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    Text(text = "Go to shopping >>",style = TextStyle(fontWeight = FontWeight(800), fontSize = 20.sp, color = Color(
                        0xFFEB5628
                    )
                    ),  textAlign = TextAlign.Center,modifier = Modifier.padding(top=16.dp).fillMaxWidth().clickable {
                        navController.popBackStack()
                    })
                }


            }
        }
        if(cartsData.isNotEmpty()){
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically){
                        OutlinedTextField(
                            value = "",
                            onValueChange = { },
                            placeholder = {
                                Text(
                                    text = "Enter your promo code",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.height(IntrinsicSize.Min),
                                    color = Color.Gray
                                )
                            },
                            modifier = Modifier
                                .background(Color.White)
                                .weight(1f)
                                .height(50.dp)
                                .shadow(2.dp, shape = RoundedCornerShape(4.dp)),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                        Image(painter = painterResource(id = R.drawable.ic_next_promo), contentDescription = "" , modifier = Modifier
                            .size(46.dp)
                            .padding(top = 1.dp))
                    }
                    Row(horizontalArrangement =  Arrangement.SpaceBetween, modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)){
                        subText3(label = "Total")
                        H4("$${totalCost}")
                    }
                    ButtonCustom(text = "Check out") {
                        navController.navigate(Screens.Payment.route)
                    }
                }
            }
        }
    }

}

