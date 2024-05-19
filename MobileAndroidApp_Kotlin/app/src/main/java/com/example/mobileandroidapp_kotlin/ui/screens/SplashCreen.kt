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
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.mobileandroidapp_kotlin.R // Đảm bảo rằng bạn import đúng tệp nguồn

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
//        delay(3000)
//        navController.navigate("signin")
    }

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
            onClick = { navController.navigate("signin")},
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = (0.75f) * screenHeight) // Cách top 3/4 màn hình
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .background(color = Color.Black),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text("Splash Screen", color = Color.White)
        }
    }
}


