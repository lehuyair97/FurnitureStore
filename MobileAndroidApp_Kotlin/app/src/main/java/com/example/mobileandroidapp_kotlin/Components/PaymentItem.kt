package com.example.mobileandroidapp_kotlin.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Address
import com.example.mobileandroidapp_kotlin.model.PaymentMethod
import com.example.mobileandroidapp_kotlin.model.Users

fun formatCreditCardNumberDisplay(input: String): String {
    val regex = "(\\d{4})(\\d{4})(\\d{4})(\\d{4})".toRegex()
    return regex.replace(input, "$1 $2 $3 $4")
}
fun parseCreditCardNumber(input: String): Long {
    return input.replace("\\s".toRegex(), "").toLong()
}
@Composable
fun PaymentItem(userName:String, paymentMethod: PaymentMethod, isChecked:Boolean=false, onCheckedChange : (Boolean)-> Unit={} ){

    Column(Modifier.fillMaxWidth()){
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(1.8f)
                .align(Alignment.CenterHorizontally)
        ){
            Image(painter = painterResource(id = R.drawable.template_cart_blank), contentDescription = "",
                modifier=Modifier.fillMaxSize()
            , contentScale = ContentScale.FillBounds)
            Text(text = formatCreditCardNumberDisplay(paymentMethod.CardNumber.toString()), modifier = Modifier.padding(top= 80.dp, start = 25.dp), style = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 130.dp, start = 25.dp)){
                Column(modifier = Modifier.weight(2f)){
                    Text(text = "Card Holder Name",style = TextStyle(fontWeight = FontWeight(400), color = Color(0xFFE0D9D9)), maxLines = 1, modifier = Modifier.padding(bottom = 8.dp))
                    Text(text =  userName,style = TextStyle(fontWeight = FontWeight(700), fontSize = 15.sp, color = Color.White))
                }
                Column(modifier = Modifier.weight(1f)){
                    Text(text = "Expiry Date",style = TextStyle(fontWeight = FontWeight(400), color = Color(0xFFE0D9D9)), maxLines = 1,modifier = Modifier.padding(bottom = 8.dp))
                    Text(text =  paymentMethod.YearMonth,style = TextStyle(fontWeight = FontWeight(700), fontSize = 15.sp, color = Color.White))
                }
            }
        }
            Row(modifier = Modifier.padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {onCheckedChange(it)}
                )
                Text(text = "Use as the default payment method", style = TextStyle(fontSize = 16.sp))
            }
   }
}

@Composable
fun CollapsePaymentItem(paymentMethod: PaymentMethod){
    Row(
        verticalAlignment = Alignment.CenterVertically
        ,modifier = Modifier
            .padding(top = 2.dp)
            .fillMaxWidth()
            .shadow(5.dp, shape = RectangleShape, clip = true)
            .background(Color.White)
            .padding(20.dp)

    ){
        Image(painter = painterResource(id = R.drawable.ic_visa_card), contentDescription = "", modifier = Modifier.padding(end = 25.dp).width(70.dp).height(38.dp), contentScale = ContentScale.FillBounds)
        H5(label = "**** **** **** ${formatCreditCardNumberDisplay(paymentMethod.CardNumber.toString().takeLast(4))} " )
    }
}