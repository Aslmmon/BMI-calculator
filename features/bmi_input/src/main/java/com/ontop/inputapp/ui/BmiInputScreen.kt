package com.ontop.inputapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.ContentWithTitle
import com.ontop.inputapp.shared_ui.GenderView
import com.ontop.inputapp.shared_ui.RoundedCardView
import com.ontop.inputapp.shared_ui.TitleScreen
import com.ontop.inputapp.shared_ui._gap


@Composable
fun BmiInputScreen(
    modifier: Modifier = Modifier,
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
//    val startText by upperCaseInputViewModel.startText.collectAsState()
//    val resultText by upperCaseInputViewModel.resultText.collectAsState()

    Column(
        modifier = modifier
    ) {
        TitleScreen(modifier, stringResource(id = R.string.bmi_calculator_title))
        ContentWithTitle(title = stringResource(R.string.gender_title)) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                RoundedCardView(modifier = modifier) {
                    GenderView(
                        modifier = modifier, icon = R.drawable.male, genderText = stringResource(
                            R.string.male
                        )
                    )
                }
                RoundedCardView(modifier = modifier) {
                    GenderView(
                        modifier = modifier, icon = R.drawable.female, genderText = stringResource(
                            R.string.female
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ShowBMiInputScreen() {
    BmiInputScreen()
}
