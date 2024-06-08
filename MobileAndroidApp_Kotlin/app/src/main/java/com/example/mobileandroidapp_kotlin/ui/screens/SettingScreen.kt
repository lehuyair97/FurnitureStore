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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.CollapsePaymentItem
import com.example.mobileandroidapp_kotlin.Components.H3
import com.example.mobileandroidapp_kotlin.Components.H4
import com.example.mobileandroidapp_kotlin.Components.H5
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.SectionCustom
import com.example.mobileandroidapp_kotlin.Components.ShippingAddressItem
import com.example.mobileandroidapp_kotlin.Components.TextFieldCustom
import com.example.mobileandroidapp_kotlin.Components.ToggleCustom
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
fun SettingScreen(navController: NavController, viewModel: MainViewModel){

    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    val user by viewModel.currentUser.collectAsState();
    var name by remember {
        mutableStateOf(user?.userName ?:"")
    }
    var email by remember {
        mutableStateOf(user?.email ?:"example@gmail.com")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
    ){
        HeadNav(Icons.Default.ArrowBackIos, title = Screens.SettingScreen.label, leadIconClick = {navController.popBackStack()})
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier= Modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            subText3(label = "Personal Information")
            Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                    })
        }
        TextFieldCustom(label = "Name", textState = name, onValueChange = {name = it}, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 5.dp))
        TextFieldCustom(label = "Email", textState = email, onValueChange = {email = it}, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 5.dp))

        Spacer(Modifier.height(8.dp))
        Row(
            modifier= Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            subText3(label = "Password")
            Icon(imageVector = Icons.Default.Edit, contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                    })
        }
        TextFieldCustom(label = "Password", textState = "**********", modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Notifications",style = TextStyle(fontWeight = FontWeight(500), color = Color(0xff606060), fontSize = 18.sp), modifier = Modifier.padding(horizontal = 10.dp))
        Spacer(Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .shadow(5.dp, shape = RectangleShape, clip = true)
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H4(label = "Sales")
            ToggleCustom()
        }
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .shadow(5.dp, shape = RectangleShape, clip = true)
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H4(label = "New arivals")
            ToggleCustom()
        }
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .shadow(5.dp, shape = RectangleShape, clip = true)
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H4(label = "Delivery status changes")
            ToggleCustom()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Help Center",style = TextStyle(fontWeight = FontWeight(500), color = Color(0xff606060), fontSize = 18.sp), modifier = Modifier.padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .shadow(5.dp, shape = RectangleShape, clip = true)
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H4(label = "Faq")
            Image(imageVector =Icons.Default.ArrowForwardIos , contentDescription = "", modifier = Modifier.clickable {
            })
        }




    }




}