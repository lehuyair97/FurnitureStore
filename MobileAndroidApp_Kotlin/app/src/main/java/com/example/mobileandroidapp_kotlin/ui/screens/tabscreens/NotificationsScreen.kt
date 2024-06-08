package com.example.mobileandroidapp_kotlin.ui.screens.tabscreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.HistoryItem
import com.example.mobileandroidapp_kotlin.Components.ShippingAddressItem
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.ui.screens.AddShippingAddress
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NotificationScreens(navController: NavHostController, viewModel: MainViewModel ) {
    val orderHistory = viewModel.historyOrders.collectAsState();
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(true)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp, bottom = 70.dp)) {
        HeadNav(
            Icons.Default.Search, title = "History Orders", leadIconClick = {})
        if(orderHistory.value!= null && orderHistory.value.isNotEmpty() ){
            Column(Modifier.padding(horizontal = 10.dp, vertical = 20.dp)){
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)){
                    itemsIndexed(orderHistory.value.reversed()){
                            index, item ->
                        HistoryItem(order = item){
                            viewModel.setLastPaymentSucess(item)
                            navController.navigate(Screens.OrderDetailsScreen.route)
                        }
                    }
                }
            }
        }
    }

}