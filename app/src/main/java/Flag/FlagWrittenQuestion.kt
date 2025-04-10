package Flag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun FlagWrittenQuestion(viewModel: FlagViewModel = viewModel(), navController: NavController, flagCounterViewModel: FlagCounterViewModel = viewModel()){
    val question = viewModel.currentQuestion.value
    var correctAnswer by remember { mutableStateOf("") }
    var lastCorrectAnswer by remember { mutableStateOf("") }
    var userAnswer by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val correctCounter by flagCounterViewModel.flagCounter
    val mistakeCounter by flagCounterViewModel.flagMistakeCounter
    val totalQuestions = viewModel.getFilteredCountries().size

    if ((correctCounter + mistakeCounter) == totalQuestions){
        viewModel.resetAtTheEnd()
        flagCounterViewModel.flagCorrectCounterReset()
        navController.navigate("flagcongrats")
    }
    LaunchedEffect(mistakeCounter) {
        if (mistakeCounter == 3) {
            showDialog = true
        }
        userAnswer = ""
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
                correctAnswer = q.name.common.uppercase()
                val isCorrect = userAnswer == correctAnswer
                Card(modifier = Modifier.padding(32.dp).height(160.dp)) {
                    Text(
                        text = "Which country's flag is this?",
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
                                flagCounterViewModel.flagCorrectCounterIncrement()
                                viewModel.generateNewQuestion()
                                userAnswer = ""
                            }else{
                                lastCorrectAnswer = correctAnswer
                                flagCounterViewModel.flagMistakeCounterIncrement()
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
                text = "Mistake : $mistakeCounter/3",
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
                        flagCounterViewModel.flagCorrectCounterReset()
                        flagCounterViewModel.flagMistakeCounterReset()
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
                        flagCounterViewModel.flagCorrectCounterReset()
                        flagCounterViewModel.flagMistakeCounterReset()
                        viewModel.generateNewQuestion()
                        showDialog = false
                    }) {
                        Text("Go home page")
                    }
                }
            )
        }
    }

}