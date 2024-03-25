package com.ontop.passwordgenerator.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ontop.passwordgenerator.ui.PasswordGeneratorScreen


const val PASSWORD_GENERATOR_ROUTE = "passwordGeneratorRoute"

fun NavController.navigateToPasswordGeneratorScreen(navOptions: NavOptions? = null) {
    val route = PASSWORD_GENERATOR_ROUTE
    navigate(route, navOptions)
}

fun NavGraphBuilder.passwordGeneratorScreen() {
    composable(
        route = PASSWORD_GENERATOR_ROUTE,
    ) {

        PasswordGeneratorScreen()
    }
}
