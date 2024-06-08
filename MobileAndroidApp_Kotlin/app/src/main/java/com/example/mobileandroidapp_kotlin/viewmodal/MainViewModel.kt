package com.example.mobileandroidapp_kotlin.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileandroidapp_kotlin.model.Carts
import com.example.mobileandroidapp_kotlin.model.District
import com.example.mobileandroidapp_kotlin.model.Furnitures
import com.example.mobileandroidapp_kotlin.model.Order
import com.example.mobileandroidapp_kotlin.model.PaymentMethod
import com.example.mobileandroidapp_kotlin.model.Province
import com.example.mobileandroidapp_kotlin.model.SignIn
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.model.UsersSignUpRequest
import com.example.mobileandroidapp_kotlin.model.Ward
import com.example.mobileandroidapp_kotlin.network.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _currentUser = MutableStateFlow<Users?>(null)
    val currentUser: MutableStateFlow<Users?> = _currentUser
    fun setCurrentUser(user: Users?){
        _currentUser.value = user;
    }

    var _selectedBottomIndex = MutableStateFlow (0);
    var selectedBottomIndex : StateFlow<Int> = _selectedBottomIndex;
    fun setSelectedBottomIndex(index: Int){
        _selectedBottomIndex.value = index
    }

    private val _showBottomNav = MutableStateFlow(true)
    val showBottomNav: StateFlow<Boolean> = _showBottomNav

    fun setShowBottomNav(show: Boolean) {
        _showBottomNav.value = show
    }

    private val _signInState = MutableStateFlow(false)
    val signInState: StateFlow<Boolean?> = _signInState
    fun setSigninState(status: Boolean) {
        _signInState.value = status
    }
    private val _signUpState = MutableStateFlow(false)
    val signUpState: StateFlow<Boolean?> = _signUpState
    fun setSignUpstate(status: Boolean) {
        _signUpState.value = status
    }
    private val _furnituresData = MutableStateFlow<List<Furnitures>>(emptyList())
    val furnituresData: StateFlow<List<Furnitures>> = _furnituresData



    private val _detailItem = MutableStateFlow<Furnitures?>(null)
    val detailItem: StateFlow<Furnitures?> = _detailItem

    fun setDetail(furniture: Furnitures) {
        _detailItem.value = furniture
    }

    fun signIn(request: SignIn) {
        viewModelScope.launch {
            try {
                val loginResult = RetrofitInstance.api.signIn(request)
                if (loginResult.isSuccess) {
                    _signInState.value = true
                    setCurrentUser(loginResult.user)
                    if(loginResult.user.favorites != null){
                        _favoritesFurnitures.value = loginResult.user.favorites!!
                    }
                }
            } catch (e: Exception) {
                handleNetworkError(e)
            }
        }
    }
    private var furnitureFetched = false

    fun fetchFurnitures() {
        if (!furnitureFetched) {
            viewModelScope.launch {
                try {
                    val data = RetrofitInstance.api.getFunitures()
                    data?.let {
                        _furnituresData.value = it
                        furnitureFetched = true
                    }
                } catch (e: Exception) {
                    handleNetworkError(e)
                }
            }
        }
    }


    fun signUp(request: UsersSignUpRequest) {
        viewModelScope.launch {
            try {
                val signUpResult = RetrofitInstance.api.signUp(request)
                if (signUpResult.isSuccess) {
                    setSignUpstate(true)
                }
            } catch (e: Exception) {
                handleNetworkError(e)
            }
        }
    }



    private val _cartsData = MutableStateFlow<List<Carts>>(emptyList())
    fun setCart(carts: List<Carts>){
        _cartsData.value = carts
    }
    fun addCart(cart: Carts) {
        _cartsData.update { currentList ->
            val index = currentList.indexOfFirst{it._id == cart._id}
            if (index >=0) {
                currentList.toMutableList().apply {
                    this[index] = this[index].copy(quantity = this[index].quantity?.plus(cart.quantity!!))
                }
            } else {
                currentList + cart
            }
        }
    }
    fun removeCart(cart: Carts) {
        _cartsData.update { currentList ->
            currentList - cart
        }
    }

    fun getQuantityCart(carts: Carts): Int {
        return _cartsData.value.filter {
            it._id == carts._id
        }.map{
            it.quantity
        }.firstOrNull() ?: 0
    }
    fun inCrementQuantityCart(cart: Carts){
        _cartsData.update {
                currentList -> currentList.map {
            if(it._id == cart._id) {
                it.copy(quantity = it.quantity?.plus(1))
            }else{
                it
            }
        }
        }
    }


    fun deCrementQuantityCart(cart: Carts) {
        _cartsData.update { currentList ->
            currentList.map {
                if (it._id == cart._id && it.quantity!! > 0) {
                    it.copy(quantity = it.quantity?.minus(1))
                } else {
                    it
                }
            }.filter { it.quantity!! > 0 }
        }
    }



    fun getCart() : StateFlow<List<Carts>> {
        val cardsData:  StateFlow<List<Carts>>  = _cartsData;
        return cardsData
    }

    private val _favoritesFurnitures  = MutableStateFlow<List<Carts>>(emptyList());
    val favoritesFurnitures : StateFlow<List<Carts>> = _favoritesFurnitures

    fun isFavoriteExits(furniture: Carts):Boolean{
        return favoritesFurnitures.value.contains(furniture)
    }
    fun addFavoriteFurnitures (furniture: Carts) {
        _favoritesFurnitures.update { currentList ->
            currentList + furniture
        }
    }

    fun sendFavoriteToSever(user: Users){
        viewModelScope.launch {
            val response = RetrofitInstance.api.updateFavorites(id = user._id, user);
            try {
                if(response.isSuccess){
                    _currentUser.value = response.user;
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }
        }
    }

    fun sendNewInfoToSever(user: Users){
        viewModelScope.launch {
            val response = RetrofitInstance.api.updateUserInfo(id = user._id, user);
            try {
                if(response.isSuccess){
                    _currentUser.value = response.user;
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }
        }
    }

    private val _paymentMethod  = MutableStateFlow<List<PaymentMethod>>(emptyList());
    val paymentMethod : StateFlow<List<PaymentMethod>> = _paymentMethod


    fun addPaymentMethod (paymentMethod: PaymentMethod) {
        _paymentMethod.update { currentList ->
            currentList + paymentMethod
        }
    }

    fun sendNewPaymentMethodToSever(user: Users){
        viewModelScope.launch {
            val response = RetrofitInstance.api.updatePaymentMethod(id = user._id, user);
            try {
                if(response.isSuccess){
                    _currentUser.value = response.user;
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }
        }
    }

    private val _provinces = MutableStateFlow<List<Province>>(emptyList())
    val provinces : StateFlow<List<Province>> = _provinces;

    private val _districts = MutableStateFlow<List<District>>(emptyList())
    val districts : StateFlow<List<District>> = _districts;

    private val _wards = MutableStateFlow<List<Ward>>(emptyList())
    val wards : StateFlow<List<Ward>> = _wards;
    fun fetchProvinces(){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiLocations.getProvinces();
                if(response?.results != null){
                    _provinces.value = response.results
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }

        }
    }

    fun fetchDistrict(provinceID: Int){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiLocations.getDistrict(provinceID)
                if(response?.results != null){
                    _districts.value = response.results
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }

        }
    }

    fun fetchWard(wardID: Int){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiLocations.getWard(wardID)
                if(response?.results != null){
                    _wards.value = response.results
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }

        }
    }

    private val _lastPaymentSuccess =  MutableStateFlow<Order?>(null);
    val lastPaymentSuccess: StateFlow<Order?> = _lastPaymentSuccess;
    fun setLastPaymentSucess(order: Order){
        _lastPaymentSuccess.value = order
    }
    fun sendPaymentToSever(order: Order){
        viewModelScope.launch {
            val response = RetrofitInstance.api.payment(order);
            try {
                if(response.data != null){
                    _lastPaymentSuccess.value = response.data;
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }
        }
    }

    private val _indexAddressSelected = MutableStateFlow(0);
    val indexAddressSelected : StateFlow<Int> = _indexAddressSelected;
    fun setIndexAddressSelected(index: Int){
        _indexAddressSelected.value = index;
    }

    private val _indexPaymentSelected = MutableStateFlow(0);
    val indexPaymentSelected : StateFlow<Int> = _indexPaymentSelected;
    fun setIndexPaymentSelected(index: Int){
        _indexPaymentSelected.value = index;
    }

    private val _historyOrders  = MutableStateFlow<List<Order>>(emptyList());
    val historyOrders : StateFlow<List<Order>> = _historyOrders
    fun fetchOrdersHistory(idUser:String){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getOrdersHistory(idUser);
                if(response?.data != null){
                    _historyOrders.value = response.data
                }
            }catch (e: Exception){
                handleNetworkError(e)
            }

        }
    }
        private fun handleNetworkError(e: Exception) {
        Log.e("Network Error", "Error: $e")
    }
}