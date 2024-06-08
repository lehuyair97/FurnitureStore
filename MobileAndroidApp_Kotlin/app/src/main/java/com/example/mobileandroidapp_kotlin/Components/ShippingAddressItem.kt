package com.example.mobileandroidapp_kotlin.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileandroidapp_kotlin.model.Address

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ShippingAddressItem( userName:String, address: Address, isChecked:Boolean=false, onCheckedChange : (Boolean)-> Unit={}, isEdit:Boolean?=false){
    Column(Modifier.fillMaxWidth()){
        if(isEdit ==false){
            Row(modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {onCheckedChange(it)}
                )
                Text(text = "Use as the shipping address", style = TextStyle(fontSize = 16.sp))

            }
        }
       Row(horizontalArrangement = Arrangement.SpaceBetween,
           modifier = Modifier
               .fillMaxWidth()
               .shadow(5.dp, shape = RectangleShape, clip = true)
               .background(Color.White)
               .padding(15.dp)

           ){
            H4(label = userName)
             if(isEdit == false){
                 Icon(imageVector = Icons.Default.Edit, contentDescription = "")
             }
       }

       Column(
           modifier = Modifier
               .padding(top = 2.dp)
               .fillMaxWidth()
               .shadow(5.dp, shape = RectangleShape, clip = true)
               .background(Color.White)
               .padding(15.dp)

       ){
           subText4(label = "${address?.location} ", maxLine =1 )
           Spacer(modifier = Modifier.height(5.dp))
           subText4(label = "${address?.detail} ", maxLine =1 )
       }
   }
}