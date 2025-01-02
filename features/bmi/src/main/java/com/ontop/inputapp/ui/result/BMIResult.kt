package com.ontop.inputapp.ui.result

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.ontop.inputapp.ui.input.UserInputSelection
import kotlinx.coroutines.delay

@Composable
fun BmiResultScreen(
    onReCalculateClicked: () -> Unit,
    sharedViewModel: SharedViewModel<UserInputSelection.UserData>,
    bmiResultViewModel: BMIResultViewModel = hiltViewModel()

) {
    val data = sharedViewModel.data
    val bmiResult by bmiResultViewModel.bmiResult.collectAsState()
    val bmiStatus by bmiResultViewModel.bmiStatus.collectAsState()


    val context = LocalContext.current


    LaunchedEffect(Unit) {
        bmiResultViewModel.calculateBMi(data.value!!, context)
        delay(300)

    }




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
                    text = "BMI = ${bmiResult.value} KG/M2 \n ${bmiStatus.bmiStatus?.status}) ",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                _Gap()
                Text(
                    text = bmiStatus.bmiStatus?.summary ?: "",
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontStyle = FontStyle.Italic)

                )
                _Gap()

                Text(
                    text = bmiStatus.bmiStatus?.recommendation ?: "",
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
                text = "Healthy weight for the height: 52.2 kgs - 70.6 kgs",
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