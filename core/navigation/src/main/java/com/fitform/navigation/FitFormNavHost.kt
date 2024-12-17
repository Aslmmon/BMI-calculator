package com.fitform.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ontop.home.ui.SplashScreen
import com.ontop.inputapp.ui.BmiInputScreen

const val SPLASH_ROUTE = "splashroute"
const val bmiInput_Route = "bmiInput"

@Composable
fun FitFormNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = SPLASH_ROUTE,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        composable(
            route = SPLASH_ROUTE,
        ) {
            SplashScreen(modifier, onNavigateToNextScreen = {
                navController.navigate(bmiInput_Route)
            })
        }
        composable(
            route = bmiInput_Route
        ) {

            BmiInputScreen()
        }


    }
}
