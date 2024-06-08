package com.example.mobileandroidapp_kotlin.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.TextFieldCustom
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Address
import com.example.mobileandroidapp_kotlin.model.PaymentMethod
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun AddPaymentMethod(navController: NavController, viewModel: MainViewModel){
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    val user by viewModel.currentUser.collectAsState();
    var name = user?.userName ?: "";
    val cardHolderName by remember { mutableStateOf(name) }
    var cardNumber by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var expirateDate by remember { mutableStateOf("") }
    fun formatCreditCardNumberDisplay(input: String): String {
        val regex = "(\\d{4})(\\d{4})(\\d{4})(\\d{4})".toRegex()
        return regex.replace(input, "$1 $2 $3 $4")
    }
    fun parseCreditCardNumber(input: String): Long {
        return input.replace("\\s".toRegex(), "").toLong()
    }



    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 50.dp,)
    ) {
        HeadNav(Icons.Default.ArrowBackIos, title = "Add payment method", leadIconClick = {
            navController.popBackStack()
        })
        Spacer(modifier = Modifier.height(5.dp))
        Image(painter = painterResource(id = R.drawable.template_cart_default) , contentDescription = "", modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
            .aspectRatio(1.85f), contentScale = ContentScale.FillBounds)
        Spacer(modifier = Modifier.height(5.dp))

        cardHolderName?.let {
            if (name != null) {
                TextFieldCustom(
                    label = "Card Holder Name", hint ="Ex: Le Huy", textState = name!!,onValueChange ={name = it},
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.White)
                        .fillMaxWidth())
            }
        }
        TextFieldCustom(
            label = "Cart Number", hint = " **** **** **** 3456", textState = cardNumber,onValueChange ={cardNumber = formatCreditCardNumberDisplay(it)},
            modifier = Modifier
                .padding(10.dp)
                .background(Color.White)
                .fillMaxWidth())
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            TextFieldCustom(
                label = "CVV", hint = "Ex: 123", textState = cvv,onValueChange ={cvv = it},
                modifier = Modifier
                    .background(Color.White)
                    .weight(1f))
            TextFieldCustom(
                label = "Expiration Date", hint = "03/22", textState = expirateDate,onValueChange ={expirateDate = it},
                modifier = Modifier
                    .background(Color.White)
                    .weight(1f))
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()){
                ButtonCustom(text = "ADD NEW CARD") {
                    val paymentMethodList: MutableList<PaymentMethod> = user?.paymentMethod?.toMutableList() ?: mutableListOf()
                    val newPayment = PaymentMethod(parseCreditCardNumber(cardNumber),cvv.toInt(),expirateDate )
                    paymentMethodList.add(newPayment)
                    val newUser = user?.copy(paymentMethod = paymentMethodList);
                    if (newUser != null) {
                        Log.e("Tag","value newPayment ${newPayment.toString()}")

                        Log.e("Tag","value paymentMethodlist ${paymentMethodList.toString()}")

                        Log.e("Tag","value ${newUser.toString()}")

                        viewModel.sendNewInfoToSever(newUser)
                    }
                }
            }
        }
    }
}