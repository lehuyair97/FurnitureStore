import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobileandroidapp_kotlin.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileandroidapp_kotlin.model.Screens
import com.example.mobileandroidapp_kotlin.viewmodel.MainViewModel

@Composable
fun SplashScreen(navController: NavController,viewModel: MainViewModel) {
    viewModel.fetchFurnitures()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Hình nền từ tệp nguồn trong drawable resources
        Image(
            painter = painterResource(id = R.drawable.bg_splash), // Đặt tên tệp nguồn của hình ảnh
            contentDescription = null,
            contentScale = ContentScale.FillBounds // Thiết lập scale cho hình ảnh
        )
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        Button(
            onClick = { navController.navigate(Screens.SignIn.route)},
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = (0.75f) * screenHeight)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .background(color = Color.Black),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text("Splash Screen", color = Color.White)
        }
    }
}
