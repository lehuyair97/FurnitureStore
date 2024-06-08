package com.example.mobileandroidapp_kotlin.ui.screens.tabscreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.CartItem
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.model.Carts
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavHostController, viewModel: MainViewModel ) {
    val context = LocalContext.current
    val carts by viewModel.getCart().collectAsState();
    val favorites by viewModel.favoritesFurnitures.collectAsState();

    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(true)
    }
        val cartsQt = carts.size
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp))  {
        HeadNav(Icons.Default.Search, title = "Favorites", traillingIcon = Icons.Default.ShoppingCart, leadIconClick = {}
            , traillingIconClick = {navController.navigate(
                Screens.Cart.route)
            },
            cartQuantity=cartsQt)
        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            itemsIndexed(favorites) { index , item ->
                CartItem(item, viewModel = viewModel, isFavorite = true, addToCart = {   item?.let { Carts(item!!._id, item!!.name, item!!.price, item!!.imageLink, 1) }
                    ?.let { viewModel.addCart(it) }
                    Toast.makeText(context, "Add to cart Successfully", Toast.LENGTH_SHORT).show() } )
                if(index != favorites.size-1){
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
            .padding(bottom = 90.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()){
            ButtonCustom(text = "Add all to my cart") {
                val combinedCarts = carts + favorites
                val distinctCarts = combinedCarts.distinctBy { it._id }
                viewModel.setCart(distinctCarts)
            }
        }
    }

}