package com.example.mobileandroidapp_kotlin.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileandroidapp_kotlin.Components.TextFieldCustom
import java.lang.Math.random


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Task(){
    var firstnum by remember { mutableStateOf<Int?>(null) }
    var secondNum by remember { mutableStateOf<Int?>(null) }
    var arithmetic by remember { mutableStateOf<String?>(null) }
    val thirdNum by remember { mutableStateOf<Int?>(null) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(value = firstnum.toString() , onValueChange ={firstnum = it.toInt()}, modifier = Modifier.size(45.dp)
            ,         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        OutlinedTextField(value = arithmetic.toString() , onValueChange ={ it -> arithmetic = it}, modifier = Modifier.size(45.dp)
            ,         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        OutlinedTextField(value = firstnum.toString() , onValueChange ={firstnum = it.toInt()}, modifier = Modifier.size(45.dp)
            ,         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Text("=", modifier= Modifier.padding(20.dp))
        OutlinedTextField(value = firstnum.toString() , onValueChange ={firstnum = it.toInt()}, modifier = Modifier.size(45.dp)
            ,         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }



}
//fun randomValue(firstnum: Int,){
//    firstnum =  (Math.random() * 100).toInt()
//    secondNum.value =  (Math.random() * 100).toInt()
//    firstnum.value =  (Math.random() * 100).toInt()
//    arithmetic.value = randomArith();
//
//}
//fun randomTask(){
//    val random = (Math.random()*4).toInt()
//    when(random){
//        0 -> firstnum.value = null
//        1 -> arithmetic.value = null
//        2 -> secondNum.value = null
//        3 -> thirdNum.value =null
//    }
//}

fun randomArith(): String {
    val random = (Math.random() * 4).toInt()
    return when (random) {
        0 -> "+"
        1 -> "-"
        2 -> "*"
        3 -> "/"
        else -> "+"
    }
}


fun arithmeticProcess(arith: String, firstnum: Float, secondNum: Float): Float {
    return when (arith) {
        "+" -> firstnum + secondNum
        "-" -> firstnum - secondNum
        "*" -> firstnum * secondNum
        "/" -> if (secondNum != 0f) firstnum / secondNum else throw IllegalArgumentException("Khong thể chia cho 0")
        else -> throw IllegalArgumentException("Nhập sai, nhập lại")
    }
}

