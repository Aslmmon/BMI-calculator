package com.aslmmovic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ontop.home.ui.SplashScreen
import com.ontop.inputapp.shared.SharedViewModel
import com.ontop.inputapp.ui.input.BmiInputScreen
import com.ontop.inputapp.ui.input.UserState
import com.ontop.inputapp.ui.result.BmiResultScreen

const val SPLASH_ROUTE = "splashroute"
const val BMI_Input = "bmiInput"
const val BMI_Result = "bmiResult"


@Composable
fun FitFormNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = SPLASH_ROUTE,
    navController: NavHostController = rememberNavController()
) {
    val sharedViewModel = viewModel<SharedViewModel<UserState.UserData>>()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        composable(
            route = SPLASH_ROUTE,
        ) {
            SplashScreen(modifier, onNavigateToNextScreen = {
                navController.navigate(BMI_Input)
            })
        }
        composable(
            route = BMI_Input
        ) {

            BmiInputScreen(
                onCalculateClicked = { navController.navigate(BMI_Result) },
                sharedViewModel = sharedViewModel
            )
        }
        composable(
            route = BMI_Result
        ) {
            BmiResultScreen(onReCalculateClicked = {
                navController.popBackStack()
            }, sharedViewModel = sharedViewModel)

        }


    }
}
