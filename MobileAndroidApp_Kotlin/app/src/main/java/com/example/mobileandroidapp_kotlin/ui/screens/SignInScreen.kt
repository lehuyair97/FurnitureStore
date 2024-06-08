package com.example.mobileandroidapp_kotlin.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.SignIn
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.ui.theme.MobileAndroidApp_KotlinTheme
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicator 1
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .weight(2f)
                    .background(Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_bench),
                contentDescription = "My Image",
                modifier= Modifier
                    .padding(20.dp)
                    .size(60.dp)
            )
            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(1.dp)
                    .background(Color.Black)
            )
        }
        Text(text = "Hello !", style = TextStyle(fontSize = 32.sp), color= Color(0xFF909090))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "WELCOME BACK", style = TextStyle(fontSize = 28.sp), color= Color.Black)
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }
            ,modifier= Modifier
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ))
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") },
            trailingIcon = {
                var iconResourceId = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_invisibility
                Icon(
                    painter = painterResource(iconResourceId),
                    contentDescription = "Password Icon",
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible },
                    tint = Color.Gray
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
        ))

        Spacer(modifier = Modifier.height(25.dp))
        TextButton(onClick = {}) {
            Text(text = "Forgot Pasword", style = TextStyle(fontWeight = FontWeight(550), fontSize = 18.sp, textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
                color= Color.Black)
        }
        Spacer(modifier = Modifier.height(15.dp))

        ButtonCustom("Sign In"){
            viewModel.signIn(SignIn(email, password))
        }
        Spacer(modifier = Modifier.height(15.dp))

        TextButton(onClick = {  navController.navigate("signup")}) {
            Text(text = "SIGN UP", style = TextStyle(fontWeight = FontWeight(550), fontSize = 18.sp, textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
                color= Color.Black)
        }



    }

    val signInState by viewModel.signInState.collectAsState()
    LaunchedEffect(signInState) {
        if (signInState == true) {
            navController.navigate("bottomtab")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    MobileAndroidApp_KotlinTheme {
        Surface {
            SignInScreen(navController = navController, viewModel = viewModel)
        }
    }
}