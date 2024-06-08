package com.example.mobileandroidapp_kotlin.ui.screens

import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.Components.ButtonCustom
import com.example.mobileandroidapp_kotlin.Components.HeadNav
import com.example.mobileandroidapp_kotlin.Components.ListSelectedCustom
import com.example.mobileandroidapp_kotlin.model.Address
import com.example.mobileandroidapp_kotlin.model.District
import com.example.mobileandroidapp_kotlin.model.Province
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.model.Ward
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShippingAddress(navController: NavController, viewModel: MainViewModel ) {
    CoroutineScope(Dispatchers.Default).launch {
        viewModel.setShowBottomNav(false)
    }
    LaunchedEffect(Unit) {
        viewModel.fetchProvinces()
    }
    val user by viewModel.currentUser.collectAsState();
    val provinces by viewModel.provinces.collectAsState()
    val districts by viewModel.districts.collectAsState()
    val wards by viewModel.wards.collectAsState()

    var selectedProvince by remember { mutableStateOf<Province?>(null) }
    var selectedDistrict by remember { mutableStateOf<District?>(null) }
    var selectedWard by remember { mutableStateOf<Ward?>(null) }

    var name by remember { mutableStateOf(user?.userName?:"") }
    var detailAddress by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        HeadNav(
            leadIcon = Icons.Default.ArrowBackIos,
            title = "Add Shipping Address",
            leadIconClick = {
                navController.popBackStack()
            }
        )


        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ListSelectedCustom(
            label = "City",
            items = provinces,
            selectedOption = selectedProvince?.province_name.orEmpty(),
            onValueChange = { province ->
                selectedProvince = province
                viewModel.fetchDistrict(province.province_id)
                selectedDistrict = null
                selectedWard = null
            },
            itemContent = { province ->
                Text(text = province.province_name, style = TextStyle(color = Color.Black))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        // District Dropdown
        ListSelectedCustom(
            label = "District",
            items = districts,
            selectedOption = selectedDistrict?.district_name.orEmpty(),
            onValueChange = { district ->
                selectedDistrict = district
                viewModel.fetchWard(district.district_id)
                selectedWard = null
            },
            itemContent = { district ->
                Text(text = district.district_name, style = TextStyle(color = Color.Black))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Ward Dropdown
        ListSelectedCustom(
            label = "Ward",
            items = wards,
            selectedOption = selectedWard?.ward_name.orEmpty(),
            onValueChange = { ward ->
                selectedWard = ward
            },
            itemContent = { ward ->
                Text(text = ward.ward_name, style = TextStyle(color = Color.Black))
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = detailAddress,
            onValueChange = { detailAddress = it },
            label = { Text(text = "Detail Address") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ButtonCustom(text = "Save Address") {
                    val listAddress: MutableList<Address> = user?.address?.toMutableList() ?: mutableListOf()
                    val ward = selectedWard?.ward_name
                    val district = selectedDistrict?.district_name
                    val province = selectedProvince?.province_name
                    val newAddress= Address("$ward - $district - $province",detailAddress)
                    listAddress.add(newAddress)
                    val newUser = user?.copy(address = listAddress);
                    viewModel.setCurrentUser(newUser)
                    if (newUser != null) {
                        Log.e("Tag","ValueUser: $listAddress")
                        viewModel.sendNewInfoToSever(newUser)
                    }
                }
            }
        }
    }
}

