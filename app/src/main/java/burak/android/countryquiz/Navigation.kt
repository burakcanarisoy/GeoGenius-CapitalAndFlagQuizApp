package burak.android.countryquiz

import Capital.CapitalCongratulationsScreen
import Capital.CapitalCounterViewModel
import Capital.CapitalDifficultyOptions
import Capital.CapitalQuestion
import Capital.CapitalViewModel
import Capital.CapitalWrittenQuestion
import Flag.FlagCongratulationsScreen
import Flag.FlagCounterViewModel
import Flag.FlagDifficultyOptions
import Flag.FlagQuestion
import Flag.FlagViewModel
import Flag.FlagWrittenQuestion
import Network.NetworkViewModel
import Network.NoInternetScreen
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(networkViewModel: NetworkViewModel = viewModel(),
               capitalCounterViewModel: CapitalCounterViewModel = viewModel(),
               flagCounterViewModel: FlagCounterViewModel = viewModel()){

    val isConnected by networkViewModel.isConnected.observeAsState(true)
    if (!isConnected){
        NoInternetScreen()
        return
    }
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as Application
    val flagViewModel: FlagViewModel = viewModel(
        factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FlagViewModel(application, flagCounterViewModel) as T
            }
        }
    )
    val capitalViewModel: CapitalViewModel = viewModel(
        factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CapitalViewModel(application, capitalCounterViewModel) as T
            }
        }
    )
    NavHost(navController = navController, startDestination = "firstscreen") {
        composable(route = "firstscreen"){
            FirstScreen(navController)
        }
        composable(route = "capitalquiz"){
            CapitalQuestion(capitalViewModel, navController, capitalCounterViewModel)
        }
        composable(route = "flagquiz"){
            FlagQuestion(flagViewModel, navController, flagCounterViewModel)
        }
        composable(route = "capitalcongrats"){
            CapitalCongratulationsScreen(navController)
        }
        composable(route = "flagcongrats"){
            FlagCongratulationsScreen(navController)
        }
        composable(route = "flag_difficulty_options"){
            FlagDifficultyOptions(navController,flagViewModel)
        }
        composable(route = "capital_difficulty_options"){
            CapitalDifficultyOptions(navController, capitalViewModel)
        }
        composable(route = "capital_mode_selector"){
            CapitalModeSelectorPage(navController)
        }
        composable(route = "flag_mode_selector"){
            FlagModeSelectorPage(navController)
        }
        composable(route = "capital_written_quiz"){
            CapitalWrittenQuestion(capitalViewModel, navController, capitalCounterViewModel)
        }
        composable(route = "flag_written_quiz"){
            FlagWrittenQuestion(flagViewModel, navController, flagCounterViewModel)
        }

    }
}