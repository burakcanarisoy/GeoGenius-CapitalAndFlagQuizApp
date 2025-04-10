package burak.android.countryquiz

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
@Composable
fun CapitalModeSelectorPage(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundimage),
            contentDescription = "Image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Please select game mode",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top= 16.dp, bottom = 24.dp),
                    style = TextStyle(shadow = Shadow(color = Color.White.copy(alpha = 0.8f), offset = Offset(2f, 2f), blurRadius = 6f))
                )
            }
            ElevatedButton(
                onClick = {
                    navController.navigate("capital_difficulty_options")
                          isModeWritten.value = false},
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(
                        painter = painterResource(id = R.drawable.multiplechoice),
                        contentDescription = "MultipeChoice Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Multiple choice",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            ElevatedButton(
                onClick = {
                    navController.navigate("capital_difficulty_options")
                          isModeWritten.value = true},
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(
                        painter = painterResource(id = R.drawable.written),
                        contentDescription = "Writing Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Written",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
@Composable
fun FlagModeSelectorPage(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundimage),
            contentDescription = "Image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Please select game mode",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top= 16.dp, bottom = 24.dp),
                    style = TextStyle(shadow = Shadow(color = Color.White.copy(alpha = 0.8f), offset = Offset(2f, 2f), blurRadius = 6f))
                )
            }
            ElevatedButton(
                onClick = {
                    navController.navigate("flag_difficulty_options")
                          isModeWritten.value = false},
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(
                        painter = painterResource(id = R.drawable.multiplechoice),
                        contentDescription = "MultipeChoice Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Multiple choice",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            ElevatedButton(
                onClick = {
                    navController.navigate("flag_difficulty_options")
                          isModeWritten.value = true},
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(
                        painter = painterResource(id = R.drawable.written),
                        contentDescription = "Writing Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Written",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
