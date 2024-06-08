package com.example.mobileandroidapp_kotlin.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileandroidapp_kotlin.model.Order

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HistoryItem( order:Order, goDetail: ()->Unit ={}){

       Column(verticalArrangement = Arrangement.spacedBy(8.dp),
           modifier = Modifier
               .fillMaxWidth()
               .shadow(5.dp, shape = RectangleShape, clip = true)
               .background(Color.White)
               .padding(15.dp)

           ){
            H4(label = "Order No. ${order._id?.takeLast(10)}")
            subText3(label = "${order.orderDate}")
       }

       Column(
           modifier = Modifier
               .padding(top = 2.dp)
               .fillMaxWidth()
               .shadow(5.dp, shape = RectangleShape, clip = true)
               .background(Color.White)
       ){
           Row(horizontalArrangement = Arrangement.SpaceBetween,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(15.dp)

           ){
              Row(){
                  subText3(label = "Quantity:")
                  H4(label = "0${order.carts?.size}")
                }
               subText3(label = "Totol Amount:  $   ${order.totalBill}")
       }
           Spacer(modifier = Modifier.height(10.dp))
           Row(horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier
                   .fillMaxWidth()
           ){
               Button(onClick = goDetail,
                   modifier = Modifier
                       .padding(10.dp),
                   colors = ButtonDefaults.buttonColors(Color.Black),
                   shape = RoundedCornerShape(8.dp)
               ) {
                   Text(text = "Detail")
               }
               Text(text = "Delivered",style = TextStyle(fontWeight = FontWeight(400), fontSize = 15.sp, color = Color.Green),
                   modifier = Modifier.padding(end=15.dp),
                   textAlign = TextAlign.Center
               )
           }
   }
}