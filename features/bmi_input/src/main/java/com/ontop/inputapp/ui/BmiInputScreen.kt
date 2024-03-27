package com.ontop.inputapp.ui

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chargemap.compose.numberpicker.ListItemPicker
import com.chargemap.compose.numberpicker.NumberPicker
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.AgeView
import com.ontop.inputapp.shared_ui.ContentWithTitle
import com.ontop.inputapp.shared_ui.GenderView
import com.ontop.inputapp.shared_ui.NumberPickers
import com.ontop.inputapp.shared_ui.RoundedCardView
import com.ontop.inputapp.shared_ui.TitleScreen


@Composable
fun BmiInputScreen(
    modifier: Modifier = Modifier,
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
//    val startText by upperCaseInputViewModel.startText.collectAsState()
//    val resultText by upperCaseInputViewModel.resultText.collectAsState()
    val (height, setHeight) = remember { mutableStateOf(160) }
    var pickerValue by remember { mutableStateOf(50) }
    var context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        TitleScreen(modifier, stringResource(id = R.string.bmi_calculator_title))
        ContentWithTitle(title = stringResource(R.string.gender_title)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
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

        ContentWithTitle(title = stringResource(R.string.height)) {
            Slider(
                value = height.toFloat(),
                onValueChange = { newValue ->
                    setHeight(newValue.toInt())
                },
                valueRange = 100f..250f,
                steps = 20,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            ContentWithTitle(modifier = modifier, title = stringResource(R.string.age_title)) {
                RoundedCardView(modifier = modifier) {
                    AgeView(
                        modifier = modifier,
                        minusIcon = R.drawable.minus,
                        plusIcon = R.drawable.plus
                    )
                }

            }
            ContentWithTitle(title = "Weight", modifier = modifier.padding(horizontal = 10.dp)) {
                RoundedCardView(modifier = modifier) {

                    NumberPickers(
                        modifier = modifier,
                        numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 23, 45),
                        onNumberSelected = { number ->
                        })

                }
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
