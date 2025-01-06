package com.ontop.inputapp.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.inputapp.R
import com.ontop.inputapp.shared.ButtonWithHyperLinkContent
import com.ontop.inputapp.shared.SharedViewModel
import com.ontop.inputapp.shared._Gap
import com.ontop.inputapp.ui.input.UserState
import kotlinx.coroutines.delay

@Composable
fun BmiResultScreen(
    onReCalculateClicked: () -> Unit,
    sharedViewModel: SharedViewModel<UserState.UserData>,
    bmiResultViewModel: BMIResultViewModel = hiltViewModel()

) {
    val data = sharedViewModel.data
    val bmiState by bmiResultViewModel.bmiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        bmiResultViewModel.calculateBMi(data.value!!, context)
        delay(300)

    }

    when (bmiState) {
        is BMIState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingScreen()
            }
        }

        is BMIState.Success -> {
            val bmiResult = (bmiState as BMIState.Success).bmiResult
            val bmiStatus = (bmiState as BMIState.Success).bmiStatus
            val healthyWeight = (bmiState as BMIState.Success).healthyWeight

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Result",
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                _Gap()
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center // Center content of the Box
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "BMI = ${bmiResult.value} KG/M2 \n ${bmiStatus.bmiItem?.status}) ",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        _Gap()
                        Text(
                            text = bmiStatus.bmiItem?.summary ?: "",
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontStyle = FontStyle.Italic)

                        )
                        _Gap()

                        Text(
                            text = bmiStatus.bmiItem?.recommendation ?: "",
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontStyle = FontStyle.Italic)

                        )

                    }
                }

                _Gap()

                Box(
                    modifier = Modifier
                        .border(
                            width = 0.5.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(11.dp)),
                    contentAlignment = Alignment.Center // Center content of the Box
                ) {

                    Text(
                        text = "Healthy weight for the height: ${healthyWeight.value} kgs ",
                        color = MaterialTheme.colorScheme.surface, fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )


                }

            }

            ButtonWithHyperLinkContent(
                buttonText = R.string.new_calculate,
                onCalculateClicked = onReCalculateClicked
            )
        }

        is BMIState.Error -> {
            val errorMessage = (bmiState as BMIState.Error).message
            Text("Error: $errorMessage", color = Color.Red)
        }
    }


}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .height(120.dp)
            .width(120.dp)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            ), // Set background color
        contentAlignment = Alignment.Center // Center content
    ) {
        CircularProgressIndicator(strokeWidth = 1.5.dp, color = MaterialTheme.colorScheme.primary)
    }
}