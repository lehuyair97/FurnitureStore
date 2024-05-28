package com.example.mobileandroidapp_kotlin.ui.screens.tabscreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Categories
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController,viewModel: MainViewModel = hiltViewModel()) {
    val categoryList: List<Categories> = listOf(
        Categories("All", icon = painterResource(id = R.drawable.ic_all)),
        Categories("Chair", icon = painterResource(id = R.drawable.ic_chair)),
        Categories("Sofa", icon = painterResource(id = R.drawable.ic_sofa)),
        Categories("Lamp", icon = painterResource(id = R.drawable.ic_lamp)),
        Categories("Cabinet", icon = painterResource(id = R.drawable.ic_cabinet)),
        Categories("Bed", icon = painterResource(id = R.drawable.ic_bed)),

    )
    val funituresData by viewModel.furnituresData.collectAsState()
    var isPressed by remember { mutableStateOf(false) }
    var itemSelectedName by remember { mutableStateOf("") }
    val cartsQuantity by viewModel.getCart().collectAsState();
    val cartsQt = cartsQuantity.size
    val currentUser by viewModel.currentUser.collectAsState();
    Log.e("User Information", "User Info: $currentUser")



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp)) {
        HeadNav(Icons.Default.Search, title = "Beautifull", subTitle = "Make Home", Icons.Default.ShoppingCart, leadIconClick = {}
            , traillingIconClick = {navController.navigate(
            Screens.Cart.route)
        },
            cartQuantity=cartsQt)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(categoryList) { item ->
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                            horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(42.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = item.label)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val valueModal by viewModel.detailItem.collectAsState();

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(funituresData.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    rowItems.forEach { furniture ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    Log.e("Tag", "this is: " + furniture.name)
                                    Log.e("Tag", "value furniture: " + furniture)
                                    itemSelectedName = furniture.name
                                    CoroutineScope(Dispatchers.Default).launch {
                                        isPressed = true
                                        delay(120)
                                        if (isPressed) {
                                            isPressed = false
                                        }
                                    }
                                    viewModel.setDetail(furniture);
                                    Log.e("Tag", "value viewModal: " + valueModal)
                                    navController.navigate("detail")
                                }
//                                .background(Color.White,)
                                .background(
                                    if (itemSelectedName == furniture.name) {
                                        if (isPressed) Color.Gray else Color.White
                                    } else Color.White
                                )



                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(0.92f),
                                shape = RoundedCornerShape(15.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if(itemSelectedName == furniture.name) {
                                        if(isPressed) Color.Gray else Color.White
                                    } else Color.White,
                                )
                            ) {
                                    val painter = rememberImagePainter(data = furniture.imageLink)
                                    Image(
                                        painter = painter,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(3f / 4f)
                                            .padding(vertical = 10.dp)
                                    )
                            }
                            Column (
                                modifier = Modifier
                                    .padding(top = 5.dp, bottom = 10.dp)
                                    .fillMaxWidth()
                            ){
                                Text(
                                    text = furniture.name,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    maxLines = 1,
                                    style= TextStyle(fontWeight = FontWeight(700), color = Color(0xff808080),fontSize = 18.sp)
                                )
                                Text(
                                    text = "$${furniture.price}",
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    style= TextStyle(fontWeight = FontWeight(600), fontSize = 16.sp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


