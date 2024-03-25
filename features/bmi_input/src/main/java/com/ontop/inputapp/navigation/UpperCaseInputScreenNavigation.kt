package com.ontop.inputapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ontop.inputapp.ui.BmiInputScreen

const val bmiInputRoute = "bmiInputRoute"

fun NavController.navigateToBmiInputScreen(navOptions: NavOptions? = null) {
    navigate(bmiInputRoute) {
        popBackStack()
    }

}

fun NavGraphBuilder.bmiInputScreen() {
    composable(
        route = bmiInputRoute,
    ) {
        BmiInputScreen()
    }
}
