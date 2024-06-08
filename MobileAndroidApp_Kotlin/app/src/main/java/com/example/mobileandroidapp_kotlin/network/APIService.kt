import com.example.mobileandroidapp_kotlin.model.Furnitures
import com.example.mobileandroidapp_kotlin.model.HistoryResponse
import com.example.mobileandroidapp_kotlin.model.PaymentResponse
import com.example.mobileandroidapp_kotlin.model.SignIn
import com.example.mobileandroidapp_kotlin.model.UserReponse
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.model.UsersSignUpRequest
import com.example.mobileandroidapp_kotlin.model.Order
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun signIn(@Body request: SignIn) : UserReponse

    @POST("users")
    suspend fun signUp(@Body request: UsersSignUpRequest) : UserReponse
    @PUT("favorites/{id}")
    suspend fun updateFavorites(@Path("id") id: String ,@Body request: Users) : UserReponse
    @PUT("payment_method/{id}")
    suspend fun updatePaymentMethod(@Path("id") id: String ,@Body request: Users) : UserReponse
    @PUT("updateInfo/{id}")
    suspend fun updateUserInfo(@Path("id") id: String ,@Body request: Users) : UserReponse
    @GET("Furniture")
    suspend fun getFunitures(): List<Furnitures>

    @POST("orders")
    suspend fun payment(@Body request: Order) : PaymentResponse

    @GET("orders/users/{id}")
    suspend fun getOrdersHistory(@Path("id") id: String) : HistoryResponse

}
