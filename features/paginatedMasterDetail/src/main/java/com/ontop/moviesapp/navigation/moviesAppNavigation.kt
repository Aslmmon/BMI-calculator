package com.ontop.moviesapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ontop.moviesapp.ui.SharedViewModel
import com.ontop.moviesapp.ui.detail.DetailScreen
import com.ontop.moviesapp.ui.master.MasterScreen
import com.ontop.network.model.Movie

const val MASTER_ROUTE = "master_route"
const val DETAIL_ROUTE = "detail_route"

fun NavController.navigateToMasterScreen(navOptions: NavOptions? = null) {
    val route = MASTER_ROUTE
    navigate(route, navOptions)
}

fun NavController.navigateToDetailsScreen(navOptions: NavOptions? = null) {
    val route = DETAIL_ROUTE
    navigate(route, navOptions)
}


fun NavGraphBuilder.masterScreen(sharedViewModel: SharedViewModel<Movie>,onNavigateToDetailScreen :() ->Unit) {
    composable(
        route = MASTER_ROUTE,
    ) {
        MasterScreen(sharedViewModel = sharedViewModel, onNavigateToDetailsScreen = onNavigateToDetailScreen)
    }
}

fun NavGraphBuilder.detailScreen(sharedViewModel: SharedViewModel<Movie>) {
    composable(
        route = DETAIL_ROUTE,
    ) {
        DetailScreen(sharedViewModel = sharedViewModel)
    }
}

