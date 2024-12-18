package com.ontop.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ontop.home.R
import kotlinx.coroutines.delay

const val splashDelay = 2000L

@Composable
fun SplashScreen(
    modifier: Modifier,
    onNavigateToNextScreen: () -> Unit,
) {


    LaunchedEffect(Unit) {
        delay(splashDelay) // Adjust delay as needed
        onNavigateToNextScreen.invoke()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {

        ComposeLottieAnimation(modifier = modifier)
        Text(
            text = stringResource(R.string.fit_form_bmi),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )

    }
}


@Composable
fun ComposeLottieAnimation(modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.fitness_man))
    LottieAnimation(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(20.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}