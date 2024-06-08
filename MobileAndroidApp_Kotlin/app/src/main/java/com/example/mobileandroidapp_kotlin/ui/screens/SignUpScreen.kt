package com.example.mobileandroidapp_kotlin.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.model.UsersSignUpRequest
import com.example.mobileandroidapp_kotlin.ui.theme.MobileAndroidApp_KotlinTheme
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var re_password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rePasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding( horizontal = 10.dp)
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

        Text(text = "WELCOME", style = TextStyle(fontSize = 28.sp), color= Color.Black)
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }
            ,modifier= Modifier
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ))
        Spacer(modifier = Modifier.height(25.dp))
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
        OutlinedTextField(value = re_password, onValueChange = { re_password = it }, label = { Text("Confirm Password") },
            trailingIcon = {
                var iconResourceId = if (rePasswordVisible) R.drawable.ic_visibility else R.drawable.ic_invisibility
                Icon(
                    painter = painterResource(iconResourceId),
                    contentDescription = "Password Icon",
                    modifier = Modifier.clickable { rePasswordVisible = !rePasswordVisible },
                    tint = Color.Gray
                )
            },
            visualTransformation = if (rePasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
            ))

        Spacer(modifier = Modifier.height(25.dp))
        ButtonCustom("Sign Up"){
            viewModel.signUp(UsersSignUpRequest(name,email,password))
        }
        Spacer(modifier = Modifier.height(15.dp))
        TextButton(onClick = { navController.navigate("signin") }) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "Already have account? ", style = TextStyle(fontSize = 15.sp), color= Color.Black)
                Text(text = "SIGN IN", style = TextStyle(fontSize = 15.sp), color= Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }



    val signUpState by viewModel.signUpState.collectAsState()
    LaunchedEffect(signUpState) {
        if (signUpState == true) {
            navController.navigate("bottomtab")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    val viewModel = remember { MainViewModel() }
    MobileAndroidApp_KotlinTheme {
        Surface {
            SignUpScreen(navController = navController, viewModel = viewModel)
        }
    }
}
