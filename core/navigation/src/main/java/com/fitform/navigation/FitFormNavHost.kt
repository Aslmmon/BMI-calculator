package com.fitform.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ontop.home.navigation.SPLASH_ROUTE
import com.ontop.home.navigation.splash
import com.ontop.inputapp.navigation.bmiInputScreen
import com.ontop.inputapp.navigation.navigateToBmiInputScreen


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
        splash(
            onNavigateToNextScreen = {
                navController.navigateToBmiInputScreen()
            },
        )
        bmiInputScreen()

    }
}
