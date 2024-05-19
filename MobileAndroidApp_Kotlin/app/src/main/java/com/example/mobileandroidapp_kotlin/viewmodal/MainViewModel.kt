package com.example.mobileandroidapp_kotlin.viewmodal

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileandroidapp_kotlin.model.SignIn
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.model.YourDataModel
import com.example.mobileandroidapp_kotlin.network.RetrofitInstance

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    // SignIn and SignUp state
    private val _signInState = MutableStateFlow(false)
    val signInState: StateFlow<Boolean?> = _signInState

    private val _signUpState = MutableStateFlow(false)
    val signUpState: StateFlow<Boolean?> = _signUpState

    // Data for BottomTab
    private val _data = MutableStateFlow<List<YourDataModel>>(emptyList())
    val data: StateFlow<List<YourDataModel>> = _data

    // Bottom navigation state
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    fun signIn(request: SignIn) {
        viewModelScope.launch {
            try {
                val loginResult = RetrofitInstance.api.signIn(request)
                Log.e("Data", "value Response: $loginResult")

                if (loginResult) {
                                    _signInState.value = true
                } else {

                }
            } catch (e: Exception) {
                Log.e("Data", "value Error: $e")
            }
//            try {
//                val response = RetrofitInstance.api.signIn(request)
//                val json = Gson().toJson(response)
//                Log.e("Data", "value Response: $json")
//                Log.e("Data", "value Response: ${response}")
////                _signInState.value = response
//            } catch (e: Exception) {
//                Log.e("Data", "value Error: $e")
//            }
        }
    }
//    fun getUsers() {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.getUsers()
//                val json = Gson().toJson(response)
//                Log.e("Data", "Values: $json")
//            } catch (e: Exception) {
//                // Xử lý nếu có lỗi xảy ra
//                Log.e("Error", "Error getting users: ${e.message}")
//            }
//        }
//    }


    fun signUp(request: Users) {
        viewModelScope.launch {
            try {
                val signUpResult = RetrofitInstance.api.signUp(request)
                Log.e("Data", "value Response: $signUpResult")
                if (signUpResult.isSuccess) {
                    _signUpState.value = true
                    val user = signUpResult.user // Trích xuất thông tin người dùng từ phản hồi
                    Log.e("Data", "User Values: ${user.userName}, ${user.email}, ${user.avatar}")

                    // Bây giờ bạn có thể sử dụng thông tin người dùng theo cách bạn muốn ở đây
                } else {
                    // Xử lý trường hợp không thành công nếu cần
                }
            } catch (e: Exception) {
                Log.e("Data", "value Error: $e")
                // Xử lý lỗi nếu cần
            }
        }
    }


    fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getYourData()
                _data.value = response
            } catch (e: Exception) {

            }
        }
    }

    fun onTabSelected(index: Int) {
        _selectedTab.value = index
    }
}