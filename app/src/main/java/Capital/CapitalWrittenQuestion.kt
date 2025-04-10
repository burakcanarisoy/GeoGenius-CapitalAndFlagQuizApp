package Capital

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import burak.android.countryquiz.R
import coil3.compose.AsyncImage

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CapitalWrittenQuestion(viewModel: CapitalViewModel = viewModel(), navController: NavController, capitalCounterViewModel: CapitalCounterViewModel = viewModel() ){

    val question = viewModel.currentQuestion.value
    var correctAnswer by remember { mutableStateOf("") }
    var lastCorrectAnswer by remember { mutableStateOf("") }
    val capitalMistakeCounter by capitalCounterViewModel.capitalMistakeCounter
    var userAnswer by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val correctCounter by capitalCounterViewModel.capitalCounter
    val totalQuestions = viewModel.getFilteredCountries().size

    if ((correctCounter + capitalMistakeCounter) == totalQuestions){
        viewModel.resetAtTheEnd()
        capitalCounterViewModel.capitalCorrectCounterReset()
        navController.navigate("capitalcongrats")
    }
    LaunchedEffect(capitalMistakeCounter) {
        if (capitalMistakeCounter == 3){
            showDialog = true
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundimage),
            modifier = Modifier.matchParentSize(),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            question?.let { q ->
                correctAnswer = q.capital?.firstOrNull()?.uppercase() ?: ""
                val isCorrect = userAnswer == correctAnswer
                Card(modifier = Modifier.padding(32.dp)) {
                    Text(
                        text = "What is the capital of ${q.name.common}?",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(model = q.flags.png, contentDescription = "Flag", modifier = Modifier.size(120.dp), alignment = Alignment.Center)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = userAnswer,
                        onValueChange = { userAnswer = it.uppercase()},
                        label = { Text("Answer") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    ElevatedButton(
                        onClick = {
                            if (isCorrect){
                                capitalCounterViewModel.capitalCorrectCounterIncrement()
                                viewModel.generateNewQuestion()
                                userAnswer = ""
                            }else{
                                lastCorrectAnswer = correctAnswer
                                capitalCounterViewModel.capitalMistakeCounterIncrement()
                                viewModel.generateNewQuestion()
                                userAnswer = ""
                            }
                        },
                    ) {
                        Text("Answer")
                    }
                }
            }
            Text(
                text = "Correct :$correctCounter/$totalQuestions",
                fontFamily = FontFamily.Cursive,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                style = TextStyle(shadow = Shadow(color = Color.White.copy(alpha = 0.8f), offset = Offset(2f, 2f), blurRadius = 6f))
            )
            Text(
                text = "Mistake : $capitalMistakeCounter/3",
                fontFamily = FontFamily.Cursive,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                style = TextStyle(shadow = Shadow(color = Color.White.copy(alpha = 0.8f), offset = Offset(2f, 2f), blurRadius = 6f))
            )
        }
        if (showDialog){
            AlertDialog(
                onDismissRequest = {},
                title = { Text(text = "Wrong Answer") },
                text = { Text(text = "The correct answer was $lastCorrectAnswer. Do you want to try again?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.resetQuestions()
                        capitalCounterViewModel.capitalCorrectCounterReset()
                        capitalCounterViewModel.capitalMistakeCounterReset()
                        viewModel.generateNewQuestion()
                        showDialog = false
                    }) {
                        Text("Try again")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        navController.navigate("firstscreen"){
                            popUpTo(navController.graph.startDestinationId){inclusive = true}
                        }
                        viewModel.resetQuestions()
                        capitalCounterViewModel.capitalCorrectCounterReset()
                        capitalCounterViewModel.capitalMistakeCounterReset()
                        showDialog = false
                    }) {
                        Text("Go home page")
                    }
                }
            )
        }
    }
}