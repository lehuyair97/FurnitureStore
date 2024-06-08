package com.example.mobileandroidapp_kotlin.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Carts
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItem(cart: Carts,isFavorite: Boolean = false, viewModel: MainViewModel, addToCart: ()->Unit = {},removeCart: ()->Unit = {} ){
    var quantity by remember{ mutableStateOf(viewModel.getQuantityCart(cart)) }
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
        ){
        Image(painter = rememberImagePainter(data =cart.imageLink)
            , contentDescription = "",
            modifier = Modifier
                .weight(0.7f)
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(7.dp)),
                contentScale = ContentScale.Fit)
        Column (modifier = Modifier
            .weight(1.8f)
            .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            ){
            subText4(label = cart.name, maxLine =1)
            Spacer(modifier = Modifier.height(8.dp))
            H4(label = "$${cart.price}")
            if(!isFavorite) Row(verticalAlignment = Alignment.CenterVertically,) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.inCrementQuantityCart(cart)
                            quantity++;
                        }
                )
                OutlinedTextField(
                    value = quantity.toString(),
                    onValueChange = {val newQuantity = it.toIntOrNull() ?: 0
                        if (it.length <= 2) {
                            quantity = newQuantity
                        }},
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .width(60.dp),
                    textStyle = TextStyle(fontSize = 15.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent
                    )
                )


                Image(painter = painterResource(id = R.drawable.ic_minus),"", modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if (quantity > 0) {
                            viewModel.deCrementQuantityCart(cart)
                            quantity--;
                        }
                    })
            }
        }
        Column(verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
            ,modifier = Modifier
                .weight(0.5f)
                .height(70.dp)) {
            Icon(imageVector = Icons.Filled.RemoveCircleOutline, contentDescription = "", modifier = Modifier.clickable { removeCart() } )
            if(isFavorite) Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "", modifier = Modifier.clickable { addToCart() } ) else Spacer(modifier = Modifier.size(5.dp))
        }
    }
}
@Composable
fun CollapseCartItem(cart : Carts){
    Row (
        modifier = Modifier
            .padding(top = 2.dp)
            .fillMaxWidth()
            .shadow(5.dp, shape = RectangleShape, clip = true)
            .background(Color.White)
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = rememberImagePainter(data =cart.imageLink)
            , contentDescription = "",
            modifier = Modifier
                .padding(10.dp)
                .weight(0.25f)
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(7.dp)),
            contentScale = ContentScale.Fit)
        Column(
            modifier= Modifier
                .padding(start = 10.dp)
                .weight(0.7f)

        ){
            p4("${cart.name}")
            Spacer(modifier = Modifier.height(4.dp))
            p4("$${cart.price}")
            Spacer(modifier = Modifier.height(4.dp))
            p4(" Quantity: ${cart.quantity}")
        }
    }
}
