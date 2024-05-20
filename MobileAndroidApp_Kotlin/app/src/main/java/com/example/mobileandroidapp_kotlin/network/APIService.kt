import com.example.mobileandroidapp_kotlin.model.Furnitures
import com.example.mobileandroidapp_kotlin.model.SignIn
import com.example.mobileandroidapp_kotlin.model.SignUpResponse
import com.example.mobileandroidapp_kotlin.model.Users
import com.example.mobileandroidapp_kotlin.model.YourDataModel
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface ApiService {
    @POST("login")
    suspend fun signIn(@Body request: SignIn) : Boolean

    @POST("users")
    suspend fun signUp(@Body request: Users) : SignUpResponse
    @GET("Furniture")
    suspend fun getFunitures(): List<Furnitures>
    @GET("your_endpoint")
    suspend fun getYourData(): List<YourDataModel>
}
