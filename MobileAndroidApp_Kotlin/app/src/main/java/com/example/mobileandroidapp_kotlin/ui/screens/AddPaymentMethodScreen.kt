package com.example.mobileandroidapp_kotlin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.TextFieldCustom
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddPaymentMethod(){
    val cardHolderName = remember { mutableStateOf("") }
    val cardNumber = remember { mutableStateOf("") }
    val cvv = remember { mutableStateOf("") }
    val expirateDate = remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 50.dp,)
    ) {
        HeadNav(Icons.Default.ArrowBackIos, title = "Add payment method", leadIconClick = {
//            navController.navigate(
//                Screens.BottomTab.route
//            )
        })
        Spacer(modifier = Modifier.height(5.dp))
        Image(painter = painterResource(id = R.drawable.template_cart_default) , contentDescription = "", modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
            .aspectRatio(1.85f), contentScale = ContentScale.FillBounds)
        Spacer(modifier = Modifier.height(5.dp))

        TextFieldCustom(label = "Card Holder Name", hint="Ex: Le Huy", textState = cardHolderName,
            modifier = Modifier
                .padding(10.dp)
                .background(Color.White)
                .fillMaxWidth())
        TextFieldCustom(label = "Cart Number",hint = " **** **** **** 3456", textState = cardNumber,
            modifier = Modifier
                .padding(10.dp)
                .background(Color.White)
                .fillMaxWidth())
        Row(
            modifier =  Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            TextFieldCustom(label = "CVV", hint= "Ex: 123", textState = cvv,
                modifier = Modifier
                    .background(Color.White)
                    .weight(1f))
            TextFieldCustom(label = "Expiration Date", hint = "03/22", textState = expirateDate,
                modifier = Modifier
                    .background(Color.White)
                    .weight(1f))
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()){
                ButtonCustom(text = "ADD NEW CARD") {
//                    navController.navigate(Screens.Payment.route)
                }
            }
        }
    }
}