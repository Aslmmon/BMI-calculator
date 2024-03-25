package com.ontop.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ontop.home.ui.SplashScreen


const val SPLASH_ROUTE = "splashroute"

fun NavGraphBuilder.splash(
    modifier: Modifier=Modifier,
    onNavigateToNextScreen: () -> Unit
) {
    composable(
        route = SPLASH_ROUTE,
    ) {
        SplashScreen(modifier, onNavigateToNextScreen)
    }
}

