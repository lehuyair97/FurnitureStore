package com.example.mobileandroidapp_kotlin.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(label:String? = null,
                    hint:String? = null,
                    textState: MutableState<String>,
                    modifier: Modifier? = null,
                    ){

    if (modifier != null) {
        OutlinedTextField(value = textState.value, onValueChange = { textState.value = it}, label = {
            if (label != null) {
                Text(label)
            }
        },
            placeholder = {
                if (hint  != null){
                    Text(hint)
                }
            },modifier= modifier,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ))
    }
}

@Composable
fun HeadNav(leadIcon: ImageVector? = null, title: String = "", subTitle: String? = null, traillingIcon: ImageVector?= null, leadIconClick : () -> Unit, traillingIconClick : ()->  Unit = {}, cartQuantity:Int = 0  ){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        if (leadIcon != null) {
            Icon(imageVector = leadIcon , contentDescription = "", tint = Color(0xFF808080), modifier = Modifier
                .size(30.dp)
                .clickable { leadIconClick() } )
        } else Spacer(modifier = Modifier.padding(5.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(subTitle !=null) Text(text = subTitle, style = TextStyle(fontSize = 25.sp, color = Color(0xFF909090)))
            Text(text = title, style = TextStyle(fontSize = 25.sp, color = Color.Black, fontWeight = FontWeight.Bold))
        }
        if (traillingIcon != null) {
            if(cartQuantity >0){
                Box(
                    modifier = Modifier.size(38.dp)
                ){
                    Icon(imageVector = traillingIcon , contentDescription = "",tint = Color(
                        0xFFAC2E2E
                    ), modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp)
                        .clickable { traillingIconClick() })
                    Text(
                        text = cartQuantity.toString(),
                        style = TextStyle(fontSize = 12.sp, color = Color.White),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(color = Color.Red, shape = CircleShape)
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                            .clip(CircleShape)
                    )
                }
            }else{
                Icon(imageVector = traillingIcon , contentDescription = "",tint = Color(0xFF808080), modifier = Modifier
                    .size(30.dp)
                    .padding(top = 5.dp)
                    .clickable { traillingIconClick() })
            }
        } else Spacer(modifier = Modifier.padding(5.dp))
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ListSelectedCustom(
    label:String,
    items: List<T>,
    selectedOption: String,
    onValueChange: (T) -> Unit,
    itemContent:@Composable (T)  -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        subText3(label = label)
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black, shape = RectangleShape),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                textColor = Color.Black
            ),

            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.clickable { expanded = true }
                )
            },

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(color = Color.White)

        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { itemContent(item) },
                    onClick = {
                        onValueChange(item)
                        expanded = false
                    }
                )
            }
        }
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
fun subText4(label: String, maxLine :Int){
    Text(text = label,style = TextStyle(fontWeight = FontWeight(400), color = Color(0xff606060)), maxLines = maxLine)
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
    Text(text = label,style = TextStyle(fontWeight = FontWeight(390), color = Color(0xff606060), textAlign = TextAlign.Justify), fontSize = 15.sp, maxLines = 6, overflow = TextOverflow.Ellipsis)}