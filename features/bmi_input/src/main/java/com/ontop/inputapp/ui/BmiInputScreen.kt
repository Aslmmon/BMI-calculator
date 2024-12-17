package com.ontop.inputapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.BMIButton
import com.ontop.inputapp.shared_ui.ContentWithTitle
import com.ontop.inputapp.shared_ui.GenderView
import com.ontop.inputapp.shared_ui.HeightViewNew
import com.ontop.inputapp.shared_ui.RoundedCardView
import com.ontop.inputapp.shared_ui.ScrollableRowList
import com.ontop.inputapp.shared_ui.TitleScreen
import com.ontop.inputapp.shared_ui._gap


@Composable
fun BmiInputScreen(
    modifier: Modifier = Modifier,
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    var selectedGender by remember { mutableStateOf<Int>(-1) }

    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        TitleScreen(modifier, stringResource(id = R.string.bmi_calculator_title))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround // Distribute items evenly
        ) {
            itemsIndexed(genders) { index, item ->
                GenderView(
                    modifier = modifier, gender = item, onGenderClicked = {
                        selectedGender = index
                    }, selectedGender == index
                )
            }
        }

        ContentWithTitle(title = stringResource(R.string.height)) {
            RoundedCardView(modifier = modifier) {
                var lists =
                    listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 23, 45, 12, 12, 12, 14, 13, 14)

                ScrollableRowList(modifier = modifier, content = { isSameIndex, item ->
                    HeightViewNew(modifier, isSameIndex, item)

                }, lists = lists)

            }

        }
        ContentWithTitle(title = stringResource(R.string.weight)) {
            RoundedCardView(modifier = modifier) {
                var lists =
                    listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 23, 45, 12, 12, 12, 14, 13, 14)

                ScrollableRowList(modifier = modifier, content = { isSameIndex, item ->
                    HeightViewNew(modifier, isSameIndex, item)

                }, lists = lists)

            }

        }

        _gap(20)
        BMIButton(
            modifier = modifier.fillMaxWidth(),
            stringResource(R.string.calculate_bmi),
            onClick = {

            })
    }
}


@Preview
@Composable
fun ShowBMiInputScreen() {
    BmiInputScreen()
}


data class gender(var name: String, var icon: Int)

var genders = mutableListOf(
    gender("Male", icon = R.drawable.male),
    gender(name = "Female", icon = R.drawable.female)
)