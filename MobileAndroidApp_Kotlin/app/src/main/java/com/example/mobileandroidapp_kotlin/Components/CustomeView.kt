package com.example.mobileandroidapp_kotlin.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
    fun ButtonCustom(text:String, onClick : ()-> Unit){
        Button(onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = text)
        }

    }

@Composable
fun HeadNav(leadIcon: ImageVector? = null, title: String = "", subTitle: String? = null, traillingIcon: ImageVector?= null  ){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        if (leadIcon != null) {
            Icon(imageVector = leadIcon , contentDescription = "", tint = Color(0xFF808080), modifier = Modifier.size(30.dp))
        } else Spacer(modifier = Modifier.padding(5.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(subTitle !=null) Text(text = subTitle, style = TextStyle(fontSize = 25.sp, color = Color(0xFF909090)))
            Text(text = title, style = TextStyle(fontSize = 25.sp, color = Color.Black, fontWeight = FontWeight.Bold))
        }
        if (traillingIcon != null) {
            Icon(imageVector = traillingIcon , contentDescription = "",tint = Color(0xFF808080), modifier = Modifier.size(30.dp))
        } else Spacer(modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun H1(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(800), fontSize = 30.sp))
}
@Composable
fun H2(label: String){
    Text(text = label, style = TextStyle(fontWeight = FontWeight(800), fontSize = 26.sp))
}
@Composable
fun H3(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(700), fontSize = 22.sp))
}
@Composable
fun H4(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(700), fontSize = 18.sp))
}
@Composable
fun H5(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(700), fontSize = 15.sp))
}
@Composable
fun subText1(label: String){
    Text(text = label, style = TextStyle(fontWeight = FontWeight(700), color = Color(0xff606060), fontSize = 26.sp))
}
@Composable
fun subText2(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(600), color = Color(0xff606060), fontSize = 22.sp))
}
@Composable
fun subText3(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(500), color = Color(0xff606060), fontSize = 18.sp))
}
@Composable
fun subText4(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(400), color = Color(0xff606060)))
}@Composable
fun p1(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(700), fontSize = 26.sp))
}
@Composable
fun p2(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(600), fontSize = 22.sp))
}
@Composable
fun p3(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(500), fontSize =  18.sp))
}
@Composable
fun p4(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(400), fontSize = 14.sp))
}

@Composable
fun Paragrapth(label: String){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(390), color = Color(0xff606060), textAlign = TextAlign.Justify), fontSize = 15.sp)}