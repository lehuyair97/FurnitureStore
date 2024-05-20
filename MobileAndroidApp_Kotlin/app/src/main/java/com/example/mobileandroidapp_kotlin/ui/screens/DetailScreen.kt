package com.example.mobileandroidapp_kotlin.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.H1
import com.example.mobileandroidapp_kotlin.Components.H3
import com.example.mobileandroidapp_kotlin.Components.H4
import com.example.mobileandroidapp_kotlin.Components.Paragrapth
import com.example.mobileandroidapp_kotlin.Components.subText3
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Furnitures
import com.example.mobileandroidapp_kotlin.viewmodal.MainViewModel
import com.google.gson.Gson
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, viewModel: MainViewModel = viewModel()){

    val item by viewModel.detailItem.collectAsState()
    Log.e("Tag","value Item ${item}")

    Column (modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier
            .fillMaxHeight(3f / 5f)
            .fillMaxWidth()){
            Image(
                painter = rememberImagePainter(data = item?.imageLink),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(3f / 4.1f)
                    .absoluteOffset(x = 70.dp, y = 0.dp)
//                    .align(BiasAlignment(1f, -1f))
                    .clip(RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp))
                    .clickable { navController.popBackStack() },
                contentScale = ContentScale.FillBounds,
            )
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .absoluteOffset(x = 54.dp, y = 60.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(5.dp),
                        clip = true
                    )
                    .background(Color.White, shape = RoundedCornerShape(5.dp))
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 160.dp)
                    .absoluteOffset(x = 42.dp)
                    .fillMaxHeight()
                    .width(65.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(30.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(30.dp))
                    .background(color = Color.White),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.ic_first_circle), "",modifier=Modifier.size(35.dp))
                Image(painter = painterResource(id = R.drawable.ic_second_circle), "",modifier=Modifier.size(35.dp))
                Image(painter = painterResource(id = R.drawable.ic_third_circle), "",modifier=Modifier.size(35.dp))
            }

        }
        var quantity by remember{ mutableStateOf(1)}
        Column (modifier = Modifier.padding(horizontal = 20.dp)){
            item?.let { H3(it.name) }
       Row(
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = 10.dp)
       ){
            H1("$ ${item?.price}")
           Row(verticalAlignment = Alignment.CenterVertically) {
               Icon(
                   imageVector = Icons.Default.Add, // Thay thế Icons.Default.Add bằng biểu tượng bạn muốn sử dụng
                   contentDescription = "Add Icon",
                   modifier = Modifier.size(24.dp) // Điều chỉnh kích thước biểu tượng tùy ý
               )
               OutlinedTextField(
                   value = quantity.toString(),
                   onValueChange = {  quantity = it.toIntOrNull() ?: 0},
                   modifier = Modifier
                       .padding(horizontal = 10.dp)
                       .size(46.dp),
                   textStyle = TextStyle(fontSize = 15.sp),
                   keyboardOptions = KeyboardOptions.Default.copy(
                       keyboardType = KeyboardType.Number
                   ),
                   singleLine = true
               )
               Image(painter = painterResource(id = R.drawable.ic_minus),"", modifier = Modifier.size(24.dp))
           }
       }
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.Star, contentDescription ="", tint = Color(
                    0xFFEC9337
                )
                )
                H4("${item?.rating}")
                Spacer(modifier = Modifier.width(30.dp))
                subText3(label = "(${item?.reviews} reviews)")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Paragrapth(label = "${item?.description}")
        }
    }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(46.dp)
                    .background(color = Color(0xFF5E5D5D), shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
            ButtonCustom(text = "Add to cart") {
            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailPreview(){
    val navController = rememberNavController()
    DetailScreen(navController = navController)
}

