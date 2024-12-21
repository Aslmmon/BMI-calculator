package com.ontop.inputapp.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.ButtonWithHyperLinkContent
import com.ontop.inputapp.shared_ui._gap
import com.ontop.inputapp.ui.input.UserInputSelection
import com.ontop.inputapp.ui.input.UserInputViewModel

@Composable
fun BmiResultScreen(
    onReCalculateClicked: () -> Unit,
    userInputViewModel: UserInputViewModel = hiltViewModel(),
) {
    val userInputSelection by userInputViewModel.userInputSelection.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Result of ${(userInputSelection as UserInputSelection.Gender).value}",
            color = MaterialTheme.colorScheme.surface,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        _gap()
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
                    text = "BMI = 19.5 KG/M2 \n (Normal) ",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                _gap()
                Text(
                    text = "A BMI of 18.5 kg/m2-25 kg/m2 indicates that you are at a healthy weight for your height" +
                            "By maintaining a healthy weight, you lower your risk of developing serious health problems. ",
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontStyle = FontStyle.Italic)

                )

            }
        }

        _gap()

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